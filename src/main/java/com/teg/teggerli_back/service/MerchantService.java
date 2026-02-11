package com.teg.teggerli_back.service;

import com.teg.teggerli_back.domain.enums.PaymentMethod;
import com.teg.teggerli_back.domain.enums.UserRole;
import com.teg.teggerli_back.domain.users.Merchant;
import com.teg.teggerli_back.dto.domain.CreateMerchantDTO;
import com.teg.teggerli_back.dto.domain.MerchantResponseDTO;
import com.teg.teggerli_back.repository.MerchantRepository;
import org.springframework.stereotype.Service;
import com.teg.teggerli_back.mappers.MerchantMapper;
@Service
public class MerchantService {

    private MerchantRepository merchantRepository;
    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public MerchantResponseDTO createMerchant(CreateMerchantDTO merchant){
        Merchant m = new Merchant();
        m.setName(merchant.name());
        m.setShopName(merchant.shopName());
        m.setShopPaymentPhone(merchant.shopPaymentPhone());
        m.setShopPaymentMethod(merchant.shopPaymentMethod());
        m.setRole(UserRole.MERCHANT);
        m.setPassword(merchant.password());

        Merchant createMerchant = merchantRepository.save(m);
        return MerchantMapper.toResponse(createMerchant);
    }
}
