package com.tendwa.zobbackend.discovery.dtos.requests;

import lombok.Data;

@Data
public class EndpointMap{
    private String endpointPath;
    private String method;
    private String endpointType;
}
