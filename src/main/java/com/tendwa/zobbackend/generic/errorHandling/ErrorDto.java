package com.tendwa.zobbackend.generic.errorHandling;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorDto {
    private String code;
    private String message;
    private Map<String, ?> details =  Collections.emptyMap();

    public ErrorDto(String badRequest, String message) {
    }
}
