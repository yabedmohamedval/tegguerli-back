package com.teg.teggerli_back.domain.catalog;

import com.teg.teggerli_back.domain.users.Merchant;
import jakarta.persistence.*;

@Entity
@Table(name  = "products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private boolean isActive = true;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Merchant merchant;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
