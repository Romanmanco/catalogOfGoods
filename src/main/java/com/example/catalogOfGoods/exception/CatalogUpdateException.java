package com.example.catalogOfGoods.exception;

/**
 * @author Roman Manko
 * @version 1.0
 *
 * Custom exception.
 */

public class CatalogUpdateException extends Exception {
    public CatalogUpdateException(String errorMessage) {
        super(errorMessage);
    }
}
