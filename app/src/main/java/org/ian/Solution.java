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
    public boolean run(int[][] data) {
        log.info("ran solution");
        if (data == null || data.length == 0) return false;

        log.info("data: {}", data.length);
        return true;
    }
}
