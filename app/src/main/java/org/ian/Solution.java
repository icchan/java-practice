package org.ian;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the solution
 */
public class Solution {
    private static final Logger log = LoggerFactory.getLogger(Solution.class);

    @SuppressWarnings("preview")
    public void tryStructuredConcurrency() {
        try (var scope = StructuredTaskScope.open()) {

            // 2. Fork the subtasks (running on Virtual Threads)
            StructuredTaskScope.Subtask<String> task1 = scope.fork(() -> {
                sleep(10);
                return "one";
            });
            StructuredTaskScope.Subtask<String> task2 = scope.fork(() -> {
                sleep(20);
                return "two";
            });

            try {
                // 3. Wait for all subtasks to complete (either success or failure).
                // If any subtask failed, scope.join() will implicitly handle the cancellation
                // of the others.
                scope.join();
            } catch (InterruptedException e) {
                log.error("interupted", e);
            }

            // use the results here
            log.info("finished {} {} ", task1.get(), task2.get());
        }
    }

    /// Basic virtual thread example Actually this is not recommended if you need to
    /// return a value In that case please use useVThreadsBetter() instead
    public void tryVirtualThreads() {
        CompletableFuture<String> futureResult = new CompletableFuture<>();

        // Create and start a virtual thread immediately
        var vThread = Thread.ofVirtual().start(() -> {
            log.info("running in a vthread");
            sleep(100);

            futureResult.complete("done");
        });

        try {
            vThread.join(); // wait for the vthread to finish

            log.info("finished with result {}", futureResult.get());
        } catch (InterruptedException | ExecutionException e) {
            log.error("interupted", e);
            futureResult.completeExceptionally(e); // needed to ensure this thread doesnt hang forever
        }
    }

    public void useVThreadsBetter() {
        
        Supplier<String> dataSupplier = () -> {
            log.info("Supplier running on thread: {}", Thread.currentThread().getName());
            sleep(100);
            return "The Calculated Data Result";
        };

        Supplier<Integer> intSupplier = () -> {
            log.info("Supplier running on thread: {}", Thread.currentThread().getName());
            sleep(50);
            return 100;
        };

        // 2. Start the asynchronous task using vthread executor
        ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(dataSupplier, virtualExecutor);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(intSupplier, virtualExecutor);

        // wait for the result
        CompletableFuture.allOf(future1, future2);
    }

    /// Start multiple vthreads and wait for all to finish
    public void tryVirtualThreadsWithExecutor() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            executor.submit(() -> {
                log.info("starting vthread 1");
                sleep(300);
                log.info("finished vthread 1");
            });

            executor.submit(() -> {
                log.info("starting vthread 2");
                sleep(200);
                log.info("finished vthread 2");
            });

            log.info("Main thread waiting for executor shutdown...");

        } // The executor.close() method is called here, which triggers the wait.

        log.info("All virtual threads have finished, and the main thread resumed.");
    }

    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(Duration.ofMillis(milliseconds));
        } catch (InterruptedException e) {
            log.error("interupted", e);
            Thread.currentThread().interrupt();
        }
    }
}
