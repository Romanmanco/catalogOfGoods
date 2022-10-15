package com.example.catalogOfGoods.controller;

import com.example.catalogOfGoods.model.dto.CatalogRequestDto;
import com.example.catalogOfGoods.model.dto.CatalogResponseDto;
import com.example.catalogOfGoods.model.dto.NewCatalogRequestDto;
import com.example.catalogOfGoods.model.dto.UpdateCatalogResponseDto;
import com.example.catalogOfGoods.model.entity.Catalog;
import com.example.catalogOfGoods.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Roman Manko
 * @version 1.0
 * Rest controller, was realized CRUD operation.
 */

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @PostMapping
    public ResponseEntity<CatalogResponseDto> saveCatalog(@RequestBody NewCatalogRequestDto dto) throws Exception {
        CatalogResponseDto catalog = catalogService.saveCatalog(dto);

        if (catalog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(catalog, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UpdateCatalogResponseDto> updateCatalog(@RequestBody CatalogRequestDto dto) throws Exception {
        UpdateCatalogResponseDto catalogResponseDto = catalogService.updateCatalog(dto);
        if (catalogResponseDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(catalogResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEntry(@PathVariable(value = "id") Long id) throws Exception {
        try {
            return catalogService.deleteCatalog(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogResponseDto> getCatalogById(@PathVariable(value = "id") Long id) throws Exception {
        try {
            CatalogResponseDto catalogById = catalogService.getCatalogById(id);
            if (catalogById == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(catalogById, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/page/{page}/size/{size}")
    public Page<Catalog> catalog(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        return catalogService.getPage(Integer.valueOf(page.intValue()), Integer.valueOf(size.intValue()));
    }
}
