package com.tendwa.zobbackend.discovery.dtos.requests;

import com.tendwa.zobbackend.generic.enums.EndpointType;
import com.tendwa.zobbackend.generic.enums.HttpMethods;
import lombok.Data;

import java.util.UUID;

@Data
public class EndpointsReq {
    private String appService_id;
    private String path;
    private String method;
    private String endpointType;
    private String status;
}
