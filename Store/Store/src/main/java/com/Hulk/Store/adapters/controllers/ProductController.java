package com.Hulk.Store.adapters.controllers;


import com.Hulk.Store.application.services.*;
import com.Hulk.Store.domain.entidades.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProduct createProduct;
    private final GetProduct getProduct;
    private final SellProduct sellProduct;
    private final UpdateProduct updateProduct;
    private final UpdateStockProduct updateStockProduct;
    private final DeleteProduct deleteProduct;
    private final GetAllProduct getAllProduct;
    @Autowired
    public ProductController(CreateProduct createProduct,
                             GetProduct getProduct,
                             SellProduct sellProduct,
                             UpdateProduct updateProduct,
                             UpdateStockProduct updateStockProduct,
                             DeleteProduct deleteProduct,
                             GetAllProduct getAllProduct){
        this.createProduct = createProduct;
        this.getProduct = getProduct;
        this.sellProduct = sellProduct;
        this.updateProduct = updateProduct;
        this.updateStockProduct = updateStockProduct;
        this.deleteProduct = deleteProduct;
        this.getAllProduct = getAllProduct;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return createProduct.ejecutar(product);
    }
    @PostMapping("/UpdateStock/{id}")
    public Product updateStock(@PathVariable Long id, @RequestParam int cantidad){
        return updateStockProduct.ejecutar(id, cantidad);
    }

    @PostMapping("/SellProduct/{id}")
    public int sellproducts(@PathVariable Long id, @RequestParam int cantidad){
        return sellProduct.ejecutar(id, cantidad);
    }
     @GetMapping("/GetStock/{id}")
     public int getStock(@PathVariable Long id) {
         return getProduct.ejecutar(id);
     }
     @PutMapping
    public Product update(@RequestBody Product product) {
        return updateProduct.ejecutar(product);
     }

     @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        deleteProduct.ejecutar(id);
     }

     @GetMapping
    public List<Product> getAll(){
        return getAllProduct.ejecutar();
     }
}
