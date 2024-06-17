package com.Hulk.Store.adapters.repositories;

import com.Hulk.Store.domain.entidades.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ProductRepository  extends JpaRepository<Product, Long>{
}
