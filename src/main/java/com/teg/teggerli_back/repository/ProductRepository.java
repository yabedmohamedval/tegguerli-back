package com.teg.teggerli_back.repository;

import com.teg.teggerli_back.domain.catalog.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
