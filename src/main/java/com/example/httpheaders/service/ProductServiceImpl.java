package com.example.httpheaders.service;

import com.example.httpheaders.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService{
    private static Long productsCount=0L;
    private List<Product> products = new ArrayList<>();

    public ProductServiceImpl(){
        products.add(new Product(++productsCount,"Computador","Tecnologia",200000.0));
        products.add(new Product(++productsCount,"Mouse","Tecnologia",30000.0));
        products.add(new Product(++productsCount,"Silla","Oficina",400000.0));


    }
    @Override
    public List<Product> listProducts() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        return products.stream()
                .filter(p->p.getId()==id)
                .findFirst()
                .orElse(null);
    }
}
