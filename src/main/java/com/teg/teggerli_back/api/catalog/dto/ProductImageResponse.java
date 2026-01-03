package com.teg.teggerli_back.api.catalog.dto;

public record ProductImageResponse(
        Long id,
        String url,
        boolean isMain
) {}
