package com.example.catalogOfGoods.exception;

/**
 * @author Roman Manko
 * @version 1.0
 *
 * Custom exception.
 */

public class DeleteCatalogException extends Exception {
    public DeleteCatalogException(String errorMessage) {
        super(errorMessage);
    }
}
