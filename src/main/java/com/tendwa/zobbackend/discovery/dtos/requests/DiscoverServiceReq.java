package com.tendwa.zobbackend.discovery.dtos.requests;

import com.tendwa.zobbackend.discovery.entities.Endpoint;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DiscoverServiceReq {
    private String name;
    private String base_url;
    private String auth_token;
    private List<Endpoint> endpoints = new ArrayList<>();
    private List<Endpoint> canaryEndpoints = new ArrayList<>();
}
