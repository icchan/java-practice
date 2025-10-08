package org.ian;

public interface RunningCommodityPrice {
    void upsertCommodityPrice(int timestamp, int commodityPrice);
    int getMaxCommodityPrice();
}