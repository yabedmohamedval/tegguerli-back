package com.teg.teggerli_back.service;

import com.teg.teggerli_back.api.catalog.dto.CreateProductRequest;
import com.teg.teggerli_back.api.catalog.dto.ProductImageResponse;
import com.teg.teggerli_back.api.catalog.dto.ProductResponse;
import com.teg.teggerli_back.api.catalog.dto.UpdateProductRequest;
import com.teg.teggerli_back.domain.catalog.Category;
import com.teg.teggerli_back.domain.catalog.Product;
import com.teg.teggerli_back.domain.catalog.ProductImage;
import com.teg.teggerli_back.domain.users.Merchant;
import com.teg.teggerli_back.repository.CategoryRepository;
import com.teg.teggerli_back.repository.MerchantRepository;
import com.teg.teggerli_back.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          MerchantRepository merchantRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.merchantRepository = merchantRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponse create(Long merchantId, CreateProductRequest req) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new EntityNotFoundException("Merchant not found: " + merchantId));

        Product p = new Product();
        p.setName(req.name());
        p.setDescription(req.description());
        p.setPrice(req.price());
        p.setStock(req.stock());
        p.setActive(true);
        p.setMerchant(merchant);

        if (req.categoryId() != null) {
            Category cat = categoryRepository.findById(req.categoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found: " + req.categoryId()));
            p.setCategory(cat);
        }

        // Images URLs
        List<ProductImage> imgs = new ArrayList<>();
        if (req.imageUrls() != null) {
            for (String url : req.imageUrls()) {
                if (url == null || url.isBlank()) continue;
                ProductImage img = new ProductImage();
                img.setUrl(url.trim());
                img.setMain(false);
                img.setProduct(p);
                imgs.add(img);
            }
        }
        // mainImageUrl
        if (req.mainImageUrl() != null && !req.mainImageUrl().isBlank()) {
            String main = req.mainImageUrl().trim();
            boolean found = false;
            for (ProductImage img : imgs) {
                if (main.equals(img.getUrl())) {
                    img.setMain(true);
                    found = true;
                } else {
                    img.setMain(false);
                }
            }
            if (!found) {
                ProductImage img = new ProductImage();
                img.setUrl(main);
                img.setMain(true);
                img.setProduct(p);
                imgs.add(img);
            }
        } else if (!imgs.isEmpty()) {
            imgs.get(0).setMain(true);
        }
        p.setProductImages(imgs);

        Product saved = productRepository.save(p);
        return toResponse(saved);
    }

    public ProductResponse get(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
        return toResponse(p);
    }

    public Page<ProductResponse> list(String q, Long categoryId, Long merchantId, boolean activeOnly, Pageable pageable) {

        if (activeOnly) {
            if (q != null && !q.isBlank() && merchantId != null) {
                return productRepository.findByIsActiveTrueAndMerchant_IdAndNameContainingIgnoreCase(merchantId, q.trim(), pageable).map(this::toResponse);
            }
            if (q != null && !q.isBlank() && categoryId != null) {
                return productRepository.findByIsActiveTrueAndCategory_IdAndNameContainingIgnoreCase(categoryId, q.trim(), pageable).map(this::toResponse);
            }
            if (q != null && !q.isBlank()) {
                return productRepository.findByIsActiveTrueAndNameContainingIgnoreCase(q.trim(), pageable).map(this::toResponse);
            }
            if (merchantId != null) return productRepository.findByIsActiveTrueAndMerchant_Id(merchantId, pageable).map(this::toResponse);
            if (categoryId != null) return productRepository.findByIsActiveTrueAndCategory_Id(categoryId, pageable).map(this::toResponse);
            return productRepository.findByIsActiveTrue(pageable).map(this::toResponse);
        } else {
            if (q != null && !q.isBlank()) return productRepository.findByNameContainingIgnoreCase(q.trim(), pageable).map(this::toResponse);
            if (merchantId != null) return productRepository.findByMerchant_Id(merchantId, pageable).map(this::toResponse);
            if (categoryId != null) return productRepository.findByCategory_Id(categoryId, pageable).map(this::toResponse);
            return productRepository.findAll(pageable).map(this::toResponse);
        }
    }

    public ProductResponse update(Long id, UpdateProductRequest req) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));

        if (req.name() != null) p.setName(req.name());
        if (req.description() != null) p.setDescription(req.description());
        if (req.price() != null) p.setPrice(req.price());
        if (req.stock() != null) p.setStock(req.stock());
        if (req.isActive() != null) p.setActive(req.isActive());

        if (req.categoryId() != null) {
            Category cat = categoryRepository.findById(req.categoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found: " + req.categoryId()));
            p.setCategory(cat);
        }

        Product saved = productRepository.save(p);
        return toResponse(saved);
    }

    public ProductResponse setStock(Long id, int newStock) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
        p.setStock(newStock);
        return toResponse(productRepository.save(p));
    }

    public ProductResponse setActive(Long id, boolean active) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
        p.setActive(active);
        return toResponse(productRepository.save(p));
    }

    private ProductResponse toResponse(Product p) {
        Long merchantId = (p.getMerchant() != null) ? p.getMerchant().getId() : null;
        Long categoryId = (p.getCategory() != null) ? p.getCategory().getId() : null;

        List<ProductImageResponse> images = List.of();
        if (p.getProductImages() != null) {
            images = p.getProductImages().stream()
                    .sorted(Comparator.comparing(ProductImage::isMain).reversed())
                    .map(img -> new ProductImageResponse(img.getId(), img.getUrl(), img.isMain()))
                    .toList();
        }

        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getStock(),
                p.isActive(),
                p.getUpdatedAt(),
                merchantId,
                categoryId,
                images
        );
    }
}
