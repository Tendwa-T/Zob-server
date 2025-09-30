package com.tendwa.zobbackend.discovery.dtos.requests;

import lombok.Data;

@Data
public class EndpointStatusReq {
    private String endpointId;
    private String status;
}
