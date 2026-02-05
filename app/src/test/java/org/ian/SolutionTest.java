package org.ian;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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

// # Input: [2.0, 2.5, 1.8, 3.2, 3.8, 2.7] → Output: 2.0
// # Input: [3.0, 2.9, 2.8, 2.7] → Output: 0.0
// # Input: [1.5, 2.5] → Output: 1.0

// # Your previous Markdown content is preserved below:

// # # Easygo Engineering Interview Question - Max Profit
// # ## Finding Maximum Profit
// # Suppose you have access to the odds of a specific betting event over time yesterday, represented as a list:

// # * The indices are the time in minutes past the event's odds opening time.
// # * The values are the decimal odds of the event at that time.

// # For example, given an event that starts at 10:00 am, if the odds were 3.0 at 12:30 pm then `odds_yesterday[150] = 3.0`

// # Write an efficient function that takes an array of decimal odds and returns the maximum profit you could have made by placing a single bet at one time and cashing out at a later time when the odds were higher.

// # * You must place the bet before cashing out
// # * At least 1 minute must pass between placing and cashing out a bet
// # * Profit is calculated as `(cash_out_odds - bet_odds) * bet_amount`, assuming a bet of $1.

// # Example:

// # ```
// # odds_yesterday = [2.0, 2.5, 1.8, 3.2, 3.8, 2.7]

// # get_max_profit(odds_yesterday)
// # // returns 2.0 (placing a bet at odds 1.8 and cashing out at odds 3.8)
// # ```


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
