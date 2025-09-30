package org.ian;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class SolutionTest {
    @Test void run() {
        Solution solution = new Solution();

        // check it does not throw an exception
        assertDoesNotThrow(() -> solution.run());
    }    
}
