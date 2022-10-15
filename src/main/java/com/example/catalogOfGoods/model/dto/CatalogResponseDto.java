package com.example.catalogOfGoods.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Roman Manko
 * @version 1.0
 *
 * Dto for get entity.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogResponseDto {
    private Long id;
    private String name;
    private String level;
    private CatalogResponseDto parent;
}