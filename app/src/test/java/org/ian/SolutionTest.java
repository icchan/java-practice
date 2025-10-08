package org.ian;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SolutionTest {

    @Test void happyTest() {

        RunningCommodityPrice r = new Solution();
        r.upsertCommodityPrice(4, 27);
        r.upsertCommodityPrice(6, 26);
        r.upsertCommodityPrice(9, 25);

        assertEquals(27 , r.getMaxCommodityPrice());// output should be 27 which is at timestamp 4

        // Confirm that out of order timestamps still work
        r.upsertCommodityPrice(3, 30);
        assertEquals(30 , r.getMaxCommodityPrice());// output should be 27 which is at timestamp 4
    }

    @Test void overwritePriceUpTest() {

        RunningCommodityPrice r = new Solution();
        r.upsertCommodityPrice(4, 27);
        r.upsertCommodityPrice(6, 26);
        r.upsertCommodityPrice(9, 25);
        r.upsertCommodityPrice(6, 30);

        assertEquals(30 , r.getMaxCommodityPrice());// output should be 27 which is at timestamp 4
    }
    
    // Test that we can override and still return the next highest
    @Test void overwritePriceDownTest() {
        RunningCommodityPrice r = new Solution();
        r.upsertCommodityPrice(4, 27);
        r.upsertCommodityPrice(5, 26);
        r.upsertCommodityPrice(6, 26);
        r.upsertCommodityPrice(9, 25);
        r.upsertCommodityPrice(4, 24);

        assertEquals(26 , r.getMaxCommodityPrice());// output should be 27 which is at timestamp 4
    } 
}
