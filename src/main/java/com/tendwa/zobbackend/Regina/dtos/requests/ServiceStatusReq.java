package com.tendwa.zobbackend.Regina.dtos.requests;

import lombok.Data;

@Data
public class ServiceStatusReq {
    private String appService_id;
    private String status;
}
