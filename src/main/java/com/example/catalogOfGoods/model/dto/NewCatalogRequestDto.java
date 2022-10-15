package com.example.catalogOfGoods.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Roman Manko
 * @version 1.0
 *
 * Dto for add entity.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewCatalogRequestDto {
    private Long parentId;
    private String name;
}