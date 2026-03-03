package org.ian;

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
    public void run(int[] data) {
        log.info("ran solution");

        for (int i : data) {
            log.info("data: {}", i);
        }
    }
}
