package com.Hulk.Store.application.services;

import com.Hulk.Store.adapters.repositories.ProductRepository;
import com.Hulk.Store.domain.entidades.Product;
import com.Hulk.Store.domain.exceptions.InvalidProductException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class UpdateProduct {

    private final ProductRepository productRepository;
    private final Validator validator;

    @Autowired
    public UpdateProduct (ProductRepository productRepository){
        this.productRepository = productRepository;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public Product ejecutar (Product product){
        validProduct(product);
        Product existenProduct = productRepository.findById(product.getId()).orElseThrow(()-> new IllegalArgumentException("Producto no encontrado"));
        existenProduct.setNombre(product.getNombre());
        existenProduct.setCategoria(product.getCategoria());
        existenProduct.setDescripcion(product.getDescripcion());
        existenProduct.setPrecio(product.getPrecio());
        existenProduct.setStock(product.getStock());
        return  productRepository.save(existenProduct);
    }

    private void validProduct(Product product){
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        if(!violations.isEmpty()){throw new ConstraintViolationException(violations);}
        if(product.getPrecio().compareTo(BigDecimal.ZERO) <=0){throw new InvalidProductException("El precio debe ser mayor que 0");}
        if(product.getStock()<0){throw new InvalidProductException("El stock no puede ser negativo");}
    }
}
