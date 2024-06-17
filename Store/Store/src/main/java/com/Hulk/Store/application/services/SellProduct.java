package com.Hulk.Store.application.services;


import com.Hulk.Store.adapters.repositories.ProductRepository;
import com.Hulk.Store.domain.entidades.Product;
import com.Hulk.Store.domain.exceptions.InvalidProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellProduct {
    private final ProductRepository productRepository;

    @Autowired
    public SellProduct (ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product ejecutar (Long id, int cantidad) {
        if(cantidad <=0){throw new InvalidProductException("La cantidad debe ser mayor que 0");}
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        if (product.getStock() < cantidad) {
            throw new IllegalArgumentException("Stock insuficiente");
        }
        product.setStock(product.getStock() - cantidad);
        return productRepository.save(product);
    }
}
