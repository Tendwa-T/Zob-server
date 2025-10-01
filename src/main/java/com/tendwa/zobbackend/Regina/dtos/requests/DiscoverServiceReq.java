package com.tendwa.zobbackend.Regina.dtos.requests;

import lombok.Data;

@Data
public class DiscoverServiceReq {
    private String name;
    private String base_url;
    private String auth_token;
    private EndpointMap[] endpoints;
}

