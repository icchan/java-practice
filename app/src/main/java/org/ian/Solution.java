package org.ian;

import java.util.Set;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the solution
 */
public class Solution {
    private static final Logger log = LoggerFactory.getLogger(Solution.class);

    /**
     * Runs the solution
     */
    public void run(Set<Integer> set) {
        log.info("ran solution");

        // print the set
        log.info(set.toString());
    }

    /**
     * Runs the solution
     */
    public double maxProfit(double[] prices) {
        log.info("ran solution");

        double min = 0.0;
        double maxProfit = 0.0;

        for (double price: prices ) {

            if (min == 0.0 || price < min) {
                min = price;
            } else {
                double profit = BigDecimal.valueOf(price).subtract(BigDecimal.valueOf(min)).doubleValue();
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }
}
