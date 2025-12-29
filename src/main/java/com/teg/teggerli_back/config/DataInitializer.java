package com.teg.teggerli_back.config;

import com.teg.teggerli_back.domain.enums.PaymentMethod;
import com.teg.teggerli_back.domain.enums.UserRole;
import com.teg.teggerli_back.domain.users.Customer;
import com.teg.teggerli_back.domain.users.Merchant;
import com.teg.teggerli_back.repository.CustomerRepository;
import com.teg.teggerli_back.repository.MerchantRepository;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer();
        customer.setName("sidi");
        customer.setEmail("sidi@example.com");
        customer.setPhone("30305555");
        customer.setPassword("1234");
        customer.setRole(UserRole.CUSTOMER);
        customer.setAddress("17 avenue poulevard, 65000");
        Merchant merchant = new Merchant();
        merchant.setName("ali");
        merchant.setEmail("ali@example.com");
        merchant.setPhone("40405555");
        merchant.setPassword("1234");
        merchant.setRole(UserRole.MERCHANT);
        merchant.setShopPaymentPhone("31344555");
        merchant.setShopPaymentMethod(PaymentMethod.MASRVI);
        merchant.setShopName("Phones Shop");
        merchant.setShopAddress("20 Rue Anstien 37000");

        // persistance of objects
        customerRepository.save(customer);
        merchantRepository.save(merchant);
    }

}
