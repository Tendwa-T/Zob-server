package com.tendwa.zobbackend.generic.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {

        super(String.format("Resource %s not found with %s %s", resourceName, fieldName, fieldValue));
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
