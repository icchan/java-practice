package org.ian;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the solution 
 */
public class Solution implements RunningCommodityPrice {
    private static final Logger log = LoggerFactory.getLogger(Solution.class);

    // storage for prices
    private Map<Integer, Integer> prices = new HashMap<>();

    // Max Heap for finding the largest
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>();

    @Override
    public void upsertCommodityPrice(int timestamp, int commodityPrice) {

        // If this already exists, remove it from the heap
        if(prices.containsKey(timestamp)) {
            Integer tsPrice = prices.get(timestamp);
            maxHeap.remove(-tsPrice);
        }

        // save the price for this timestamp
        prices.put(timestamp, commodityPrice);

        // store the commodity price in the heap
        maxHeap.add(-commodityPrice);

        // TODO handle overriding down

    }

    @Override
    public int getMaxCommodityPrice() {
        // the "minimum" in my heap
        return -maxHeap.peek();
    }
}
