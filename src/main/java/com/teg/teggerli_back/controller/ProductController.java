package com.teg.teggerli_back.controller;

import com.teg.teggerli_back.api.catalog.dto.CreateProductRequest;
import com.teg.teggerli_back.api.catalog.dto.ProductResponse;
import com.teg.teggerli_back.api.catalog.dto.UpdateProductRequest;
import com.teg.teggerli_back.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Products list
    @GetMapping("/products")
    public Page<ProductResponse> listProducts(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long merchantId,
            @RequestParam(defaultValue = "true") boolean activeOnly,
            Pageable pageable
    ) {
        return productService.list(q, categoryId, merchantId, activeOnly, pageable);
    }

    // Product details
    @GetMapping("/products/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.get(id);
    }

    // Merchant creates its own product
    @PostMapping("/merchants/{merchantId}/products")
    public ProductResponse createProduct(@PathVariable Long merchantId,
                                         @Valid @RequestBody CreateProductRequest req) {
        return productService.create(merchantId, req);
    }

    // Update product
    @PutMapping("/products/{id}")
    public ProductResponse updateProduct(@PathVariable Long id,
                                         @Valid @RequestBody UpdateProductRequest req) {
        return productService.update(id, req);
    }

    // Quick stock update
    @PatchMapping("/products/{id}/stock")
    public ProductResponse updateStock(@PathVariable Long id,
                                       @RequestParam @Min(0) int stock) {
        return productService.setStock(id, stock);
    }

    // Activate/deactivate the product
    @PatchMapping("/products/{id}/active")
    public ProductResponse updateActive(@PathVariable Long id,
                                        @RequestParam boolean active) {
        return productService.setActive(id, active);
    }
}