package com.teg.teggerli_back.controller;

import com.teg.teggerli_back.dto.domain.CreateMerchantDTO;
import com.teg.teggerli_back.dto.domain.MerchantResponseDTO;
import com.teg.teggerli_back.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping
    public MerchantResponseDTO addMerchant(@RequestBody CreateMerchantDTO merchant){
        System.out.println("✅ /api/merchants appelé !");
        System.out.println("📦 Payload reçu: " + merchant);
        return merchantService.createMerchant(merchant);
    }
}
