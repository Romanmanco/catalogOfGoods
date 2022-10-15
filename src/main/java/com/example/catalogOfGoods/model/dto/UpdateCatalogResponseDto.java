package com.example.catalogOfGoods.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Roman Manko
 * @version 1.0
 *
 * Dto for update entity.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCatalogResponseDto {
    private Long id;
    private String name;
    private String level;
    private Long parentId;
}