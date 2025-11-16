package org.ian;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.StructuredTaskScope;

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

    public void tryVirtualThreads() {
        // Create and start a virtual thread immediately
        var vThread = Thread.ofVirtual().start(() -> {
            log.info("running in a vthread");
        });

        try {
            vThread.join(); // wait for the vthread to finish
        } catch (InterruptedException e) {
            log.error("interupted", e);
        }
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
