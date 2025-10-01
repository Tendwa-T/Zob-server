package com.tendwa.zobbackend.Regina.dtos.requests;

import lombok.Data;

@Data
public class EndpointsReq {
    private String appService_id;
    private String path;
    private String method;
    private String endpointType;
    private String status;
}
