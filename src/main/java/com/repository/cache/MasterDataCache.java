package com.repository.cache;

import com.common.Constants;
import com.entities.Product;
import com.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MasterDataCache {
    private static final Map<String, List<?>> dataMap = new HashMap<>();

    private static ProductService productService;

    private MasterDataCache(ProductService productService) {
        MasterDataCache.productService = productService;
    }

    public static List<?> getDataMap(String key) {
        if (!dataMap.isEmpty()) {
            return dataMap.get(key);
        } else {
            //get initial values
            initializeCache();

            return getDataMap(key);
        }
    }

    private static void initializeCache() {
        List<Product> productList = productService.findAll();

        dataMap.put(Constants.PRODUCTS, productList);
        // add your caching data one by one
    }
}
