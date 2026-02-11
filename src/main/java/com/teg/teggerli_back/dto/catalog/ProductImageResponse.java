package com.teg.teggerli_back.dto.catalog;

public record ProductImageResponse(
        Long id,
        String url,
        boolean isMain
) {}
