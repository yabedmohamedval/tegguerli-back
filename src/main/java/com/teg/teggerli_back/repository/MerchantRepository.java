package com.teg.teggerli_back.repository;

import com.teg.teggerli_back.domain.users.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant,Long> {
    Optional<Merchant> findByShopName(String shopName);
    Optional<Merchant> findByPhone(String phone);
}
