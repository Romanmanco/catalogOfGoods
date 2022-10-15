package com.example.catalogOfGoods.service.impl;

import com.example.catalogOfGoods.exception.CatalogUpdateException;
import com.example.catalogOfGoods.exception.DeleteCatalogException;
import com.example.catalogOfGoods.exception.InvalidParentIdException;
import com.example.catalogOfGoods.mapper.CatalogMapper;
import com.example.catalogOfGoods.model.dto.CatalogRequestDto;
import com.example.catalogOfGoods.model.dto.CatalogResponseDto;
import com.example.catalogOfGoods.model.dto.NewCatalogRequestDto;
import com.example.catalogOfGoods.model.dto.UpdateCatalogResponseDto;
import com.example.catalogOfGoods.model.entity.Catalog;
import com.example.catalogOfGoods.repository.CatalogRepo;
import com.example.catalogOfGoods.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Roman Manko
 * @version 1.0
 *
 * Implementation of CatalogService
 */

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    CatalogRepo repository;

    @Autowired
    CatalogMapper mapper;

    @Override
    public CatalogResponseDto saveCatalog(NewCatalogRequestDto dto) throws Exception {
        String rootLvl = "1.";
        Catalog catalog = new Catalog();
        catalog.setName(dto.getName());

        if (dto.getParentId() == 0) {
            Optional<List<Catalog>> parentOptionalList = repository.findByParentIsNull();
            if (parentOptionalList.isPresent() ) {
                catalog.setLevel(String.format("%s", parentOptionalList.get().size() + 1));
            }
            else {
                catalog.setLevel(rootLvl);
            }
        } else {
            Optional<Catalog> optionalParent = repository.findById(dto.getParentId());

            Catalog parent = optionalParent.get();

            catalog.setParent(parent);

            Optional<List<Catalog>> childList = repository.findByParentId(dto.getParentId());
            int childCount;
            if (childList.isEmpty()) {
                childCount = 1;
            } else {
                childCount = childList.get().size() + 1;
            }
            catalog.setLevel(parent.getLevel() + "." + childCount);
        }

        Catalog savedCatalog = repository.save(catalog);
        Optional<Catalog> byId = repository.findById(savedCatalog.getId());
        if (byId.isEmpty()) {
            throw new InvalidParentIdException("Sorry, parent not found");
        }

        return mapper.catalogToResponseDto(byId.get());
    }

    @Override
    public UpdateCatalogResponseDto updateCatalog(CatalogRequestDto dto) throws Exception {
        Optional<Catalog> optionalCatalog = repository.findById(dto.getId());
        if (optionalCatalog.isEmpty()) {
            throw new CatalogUpdateException("Sorry, parent not updated");
        }

        Optional<Catalog> optionalParent = repository.findById(dto.getParentId());
        if (optionalParent.isEmpty()) {
            throw new InvalidParentIdException("Sorry, parent not found");

        }

        Catalog catalog = optionalCatalog.get();
        catalog.setName(dto.getName());
        catalog.setParent(optionalParent.get());
        try {
            Catalog savedCatalog = repository.save(catalog);
            return mapper.updatedCatalogToResponseDto(savedCatalog);
        } catch (Exception e) {
            throw new CatalogUpdateException("Sorry, parent not updated");
        }
    }

    @Override
    public boolean deleteCatalog(Long id) throws Exception {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new DeleteCatalogException("Delete catalog exception");
        }
    }

    @Override
    public CatalogResponseDto getCatalogById(Long id) {
        Optional<Catalog> catalog = repository.findById(id);
        return mapper.catalogToResponseDto(catalog.get());
    }

    @Override
    public Page<Catalog> getPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }
}
