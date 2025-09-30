package com.tendwa.zobbackend.discovery.dtos.requests;

import lombok.Data;

import java.util.Map;

@Data
public class DiscoverServiceReq {
    private String name;
    private String base_url;
    private String auth_token;
    private EndpointMap[] endpoints;
}

