package com.teg.teggerli_back.repository;

import com.teg.teggerli_back.domain.catalog.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByIsActiveTrue(Pageable pageable);

    Page<Product> findByMerchant_Id(Long merchantId, Pageable pageable);

    Page<Product> findByCategory_Id(Long categoryId, Pageable pageable);

    Page<Product> findByIsActiveTrueAndNameContainingIgnoreCase(String q, Pageable pageable);

    Page<Product> findByNameContainingIgnoreCase(String q, Pageable pageable);

    Page<Product> findByIsActiveTrueAndCategory_Id(Long categoryId, Pageable pageable);

    Page<Product> findByIsActiveTrueAndMerchant_Id(Long merchantId, Pageable pageable);

    Page<Product> findByIsActiveTrueAndMerchant_IdAndNameContainingIgnoreCase(Long merchantId, String q, Pageable pageable);

    Page<Product> findByIsActiveTrueAndCategory_IdAndNameContainingIgnoreCase(Long categoryId, String q, Pageable pageable);

}
