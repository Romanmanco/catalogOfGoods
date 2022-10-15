package com.example.catalogOfGoods.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Roman Manko
 * @version 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogRequestDto {
    private Long id;
    private String name;
    private Long parentId;
}