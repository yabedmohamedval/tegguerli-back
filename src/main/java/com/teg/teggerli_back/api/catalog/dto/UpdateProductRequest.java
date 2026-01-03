package com.teg.teggerli_back.api.catalog.dto;

import jakarta.validation.constraints.Min;

public record UpdateProductRequest(
        String name,
        String description,
        @Min(0) Double price,
        @Min(0) Integer stock,
        Long categoryId,
        Boolean isActive
) {}