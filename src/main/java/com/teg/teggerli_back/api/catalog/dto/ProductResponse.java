package com.teg.teggerli_back.api.catalog.dto;

import java.time.Instant;
import java.util.List;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Double price,
        int stock,
        boolean isActive,
        Instant updatedAt,
        Long merchantId,
        Long categoryId,
        List<ProductImageResponse> images
) {}