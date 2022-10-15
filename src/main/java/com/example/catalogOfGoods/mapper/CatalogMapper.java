package com.example.catalogOfGoods.mapper;

import com.example.catalogOfGoods.model.dto.CatalogResponseDto;
import com.example.catalogOfGoods.model.dto.UpdateCatalogResponseDto;
import com.example.catalogOfGoods.model.entity.Catalog;
import org.springframework.stereotype.Component;

@Component
public class CatalogMapper {
    public CatalogResponseDto catalogToResponseDto(Catalog catalog) {
        CatalogResponseDto dto = new CatalogResponseDto();
        dto.setId(catalog.getId());
        dto.setName(catalog.getName());
        dto.setParent(getParent(catalog.getParent()));
        dto.setLevel(catalog.getLevel());
        return dto;
    }

    private CatalogResponseDto getParent(Catalog parent) {
        if (parent != null) {
            return catalogToResponseDto(parent);
        }
        return null;
    }

    public UpdateCatalogResponseDto updatedCatalogToResponseDto(Catalog catalog) {
        UpdateCatalogResponseDto dto = new UpdateCatalogResponseDto();
        dto.setId(catalog.getId());
        dto.setName(catalog.getName());
        dto.setParentId(catalog.getParent().getId());
        dto.setLevel(catalog.getLevel());
        return dto;
    }
}
