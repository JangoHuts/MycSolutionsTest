package com.Hulk.Store.application.services;

import com.Hulk.Store.adapters.repositories.ProductRepository;
import com.Hulk.Store.domain.entidades.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProduct {

    private final ProductRepository productRepository;

    @Autowired
    public GetAllProduct(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public List<Product> ejecutar(){
        return productRepository.findAll();
    }
}
