package org.ian;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class SolutionTest {

    @Test
    void run() {
        Solution solution = new Solution();

        // test data, list of integers
        int[] testData = {1, 2, 3, 4, 5};

        // check it does not throw an exception
        assertDoesNotThrow(() -> solution.run(testData));

    }

}
