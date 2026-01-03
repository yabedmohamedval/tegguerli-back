package com.teg.teggerli_back.api.catalog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateProductRequest(
        @NotBlank String name,
        String description,
        @NotNull @Min(0) Double price,
        @Min(0) int stock,
        Long categoryId,
        List<String> imageUrls,
        String mainImageUrl
) {}