package com.teg.teggerli_back.dto.catalog;

import jakarta.validation.constraints.Min;

public record UpdateProductRequest(
        String name,
        String description,
        @Min(0) Double price,
        @Min(0) Integer stock,
        Long categoryId,
        Boolean isActive
) {}