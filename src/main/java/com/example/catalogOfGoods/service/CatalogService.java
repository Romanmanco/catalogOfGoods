package com.example.catalogOfGoods.service;

import com.example.catalogOfGoods.model.dto.CatalogRequestDto;
import com.example.catalogOfGoods.model.dto.CatalogResponseDto;
import com.example.catalogOfGoods.model.dto.NewCatalogRequestDto;
import com.example.catalogOfGoods.model.dto.UpdateCatalogResponseDto;
import com.example.catalogOfGoods.model.entity.Catalog;
import org.springframework.data.domain.Page;

/**
 * @author Roman Manko
 * @version 1.0
 */

public interface CatalogService {

    CatalogResponseDto saveCatalog(NewCatalogRequestDto dto) throws Exception;

    UpdateCatalogResponseDto updateCatalog(CatalogRequestDto dto) throws Exception;

    boolean deleteCatalog(Long id) throws Exception;

    CatalogResponseDto getCatalogById(Long id);

    Page<Catalog> getPage(Integer page, Integer size);

}
