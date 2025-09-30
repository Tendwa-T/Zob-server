package com.tendwa.zobbackend.discovery.dtos.requests;

import lombok.Data;

@Data
public class ServiceStatusReq {
    private String appService_id;
    private String status;
}
