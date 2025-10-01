package com.tendwa.zobbackend.Regina.dtos.requests;

import lombok.Data;

@Data
public class EndpointStatusReq {
    private String endpointId;
    private String status;
}
