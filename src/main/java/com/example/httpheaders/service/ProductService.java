package com.example.httpheaders.service;

import com.example.httpheaders.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> listProducts();
    Product findById(Long id);
}
