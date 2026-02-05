package org.ian;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class SolutionTest {

    public class TestCase {
        public final double[] input;
        public final double expected;
        public TestCase(double[] input, double expected) {
            this.input = input;
            this.expected = expected;
        }
    }

    @Test
    void run() {
        Solution solution = new Solution();

        // create an empty set
        Set<Integer> set = new HashSet<>(Set.of(1, 2, 3, 4));

        // add elements to the set
        set.add(5); 
        set.add(4); // duplicate, will not be added
        set.add(3); // duplicate, will not be added

        // check it does not throw an exception
        assertDoesNotThrow(() -> solution.run(set));


        List<TestCase> testCases = List.of(
            new TestCase(new double[] {2.0, 2.5, 1.8, 3.2, 3.8, 2.7}, 2.0),
            new TestCase(new double[] {3.0, 2.9, 2.8, 2.7}, 0.0),
            new TestCase(new double[] {1.5, 2.5}, 1.0),
            new TestCase(new double[] {2.0, 2.5, 1.8, 3.2, 3.8, 1.0, 4.9, 1.7, 2.4, 3.3}, 3.9)
        );

        for (TestCase test: testCases) {
            double value = solution.maxProfit(test.input);
            System.out.println(String.format("%.1f", value));
            assertEquals(test.expected, value);
        }

    }

}
