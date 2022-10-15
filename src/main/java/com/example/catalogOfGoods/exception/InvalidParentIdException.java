package com.example.catalogOfGoods.exception;

/**
 * @author Roman Manko
 * @version 1.0
 *
 * Custom exception.
 */

public class InvalidParentIdException extends Exception {
    public InvalidParentIdException(String errorMessage) {
        super(errorMessage);
    }
}
