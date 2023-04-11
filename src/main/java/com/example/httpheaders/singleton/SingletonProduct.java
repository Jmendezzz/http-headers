package com.example.httpheaders.singleton;

import com.example.httpheaders.service.ProductService;
import com.example.httpheaders.service.ProductServiceImpl;

public class SingletonProduct {

    private static final ProductService productService = new ProductServiceImpl();

    public static ProductService getProductService(){
        return productService;
    }
    private SingletonProduct(){}
}
