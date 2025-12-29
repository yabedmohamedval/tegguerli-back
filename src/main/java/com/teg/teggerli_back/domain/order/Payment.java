package com.teg.teggerli_back.domain.order;

import com.teg.teggerli_back.domain.enums.PaymentMethod;
import com.teg.teggerli_back.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Column(nullable = false, unique = true)
    private String reference;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;

    private Instant paymentDate;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;
}
