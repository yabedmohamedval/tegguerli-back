package com.teg.teggerli_back.domain.users;

import com.teg.teggerli_back.domain.enums.PaymentMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "merchants")
@AllArgsConstructor
@NoArgsConstructor
public class Merchant extends User {

    @Column(length = 500)
    private String shopName;
    @Column(length = 500)
    private String shopAddress;

    @Column(length = 500)
    private String shopPaymentPhone;

    @Column(length = 500)
    private PaymentMethod shopPaymentMethod;
}
