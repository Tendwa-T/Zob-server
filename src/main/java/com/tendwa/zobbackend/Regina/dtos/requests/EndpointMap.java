package com.tendwa.zobbackend.Regina.dtos.requests;

import lombok.Data;

@Data
public class EndpointMap{
    private String endpointPath;
    private String method;
    private String endpointType;
}
