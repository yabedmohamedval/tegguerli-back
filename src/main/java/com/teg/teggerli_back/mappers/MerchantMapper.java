package com.teg.teggerli_back.mappers;

import com.teg.teggerli_back.domain.users.Merchant;
import com.teg.teggerli_back.dto.domain.MerchantResponseDTO;

public class MerchantMapper {
    public static MerchantResponseDTO toResponse(Merchant merchant) {
        return new MerchantResponseDTO(
                merchant.getId(),
                merchant.getName(),
                merchant.getShopName(),
                merchant.getShopPaymentPhone(),
                merchant.getShopPaymentMethod(),
                merchant.getRole()
        );
    }
}
