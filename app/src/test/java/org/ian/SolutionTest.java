package org.ian;

import org.junit.jupiter.api.Test;

public class SolutionTest {

    @Test
    void runVthread2() {
        Solution solution = new Solution();

        solution.tryVirtualThreadsWithExecutor();
    }
}
