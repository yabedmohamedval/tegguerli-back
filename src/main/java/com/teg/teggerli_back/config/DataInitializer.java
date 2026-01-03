package com.teg.teggerli_back.config;

import com.teg.teggerli_back.domain.catalog.Product;
import com.teg.teggerli_back.domain.catalog.ProductImage;
import com.teg.teggerli_back.domain.enums.PaymentMethod;
import com.teg.teggerli_back.domain.enums.UserRole;
import com.teg.teggerli_back.domain.users.Customer;
import com.teg.teggerli_back.domain.users.Merchant;
import com.teg.teggerli_back.repository.CustomerRepository;
import com.teg.teggerli_back.repository.MerchantRepository;
import com.teg.teggerli_back.repository.ProductRepository;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DataInitializer implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final MerchantRepository merchantRepository;
    private final ProductRepository productRepository;

    public DataInitializer(CustomerRepository customerRepository,
                           MerchantRepository merchantRepository,
                           ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.merchantRepository = merchantRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (customerRepository.count() > 0 || merchantRepository.count() > 0 || productRepository.count() > 0) {
            return;
        }

        // 1) Customer
        Customer customer = new Customer();
        customer.setName("sidi");
        customer.setEmail("sidi@example.com");
        customer.setPhone("30305555");
        customer.setPassword("1234");
        customer.setRole(UserRole.CUSTOMER);
        customer.setAddress("17 avenue poulevard, 65000");
        customerRepository.save(customer);

        // 2) Merchant
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
        merchantRepository.save(merchant);


        // 3) Products
        Product iphone = new Product();
        iphone.setName("iPhone 13");
        iphone.setDescription("128GB - excellent état");
        iphone.setPrice(450.0);
        iphone.setStock(5);
        iphone.setActive(true);
        iphone.setMerchant(merchant);

        iphone.setProductImages(List.of(
                img(iphone, "https://example.com/images/iphone13-1.jpg", true),
                img(iphone, "https://example.com/images/iphone13-2.jpg", false)
        ));

        Product watch = new Product();
        watch.setName("Smart Watch");
        watch.setDescription("Montre connectée - capteurs santé");
        watch.setPrice(60.0);
        watch.setStock(15);
        watch.setActive(true);
        watch.setMerchant(merchant);

        watch.setProductImages(List.of(
                img(watch, "https://example.com/images/smartwatch-1.jpg", true)
        ));

        Product car = new Product();
        car.setName("Voiture (annonce)");
        car.setDescription("Toyota Corolla 2016");
        car.setPrice(3500.0);
        car.setStock(1);
        car.setActive(true);
        car.setMerchant(merchant);

        car.setProductImages(List.of(
                img(car, "https://example.com/images/car-1.jpg", true),
                img(car, "https://example.com/images/car-2.jpg", false)
        ));

        productRepository.saveAll(List.of(iphone, watch, car));
    }

    private ProductImage img(Product product, String url, boolean isMain) {
        ProductImage i = new ProductImage();
        i.setProduct(product);
        i.setUrl(url);
        i.setMain(isMain);
        return i;
    }

}
