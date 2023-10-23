package com.company.basic_project.model;

import java.util.HashMap;
import java.util.Map;

public enum Shop {
    SCC_RO("ro-scc-shop-b2b", "ro-scc-shop-b2b");


    public final String testConfigShopId;
    public final String shopId;

    // Reverse-lookup map for getting Shop from shop ID
    private static final Map<String, Shop> lookup = new HashMap<String, Shop>();

    static {
        for (Shop shop : Shop.values()) {
            lookup.put(shop.testConfigShopId, shop);
        }
    }

    Shop(String testConfigShopId, String shopId) {
        this.testConfigShopId = testConfigShopId;
        this.shopId = shopId;
    }

    public static Shop get(String testConfigShopId) {
        return lookup.get(testConfigShopId);
    }
}
