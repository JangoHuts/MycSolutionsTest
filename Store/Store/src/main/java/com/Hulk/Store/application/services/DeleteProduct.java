package com.Hulk.Store.application.services;

import com.Hulk.Store.adapters.repositories.ProductRepository;
import com.Hulk.Store.domain.entidades.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProduct {

    private final ProductRepository productRepository;

    @Autowired
    public DeleteProduct(ProductRepository productRepository){ this.productRepository = productRepository;}

    public void ejecutar(Long id){
        Product product = productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Producto no encontrado"));
        productRepository.delete(product);
    }
}
