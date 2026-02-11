package com.teg.teggerli_back.dto.domain;

import com.teg.teggerli_back.domain.enums.PaymentMethod;
import com.teg.teggerli_back.domain.enums.UserRole;

public record MerchantResponseDTO (
        Long id,
        String name,
        String shopName,
        String shopPaymentPhone,
        PaymentMethod shopPaymentMethod,
        UserRole role

) { }
