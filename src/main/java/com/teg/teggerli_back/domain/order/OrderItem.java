package com.teg.teggerli_back.domain.order;

import com.teg.teggerli_back.domain.catalog.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Double unitPrice;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;
}
