package com.teg.teggerli_back.repository;


import com.teg.teggerli_back.domain.catalog.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}

