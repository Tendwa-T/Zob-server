package com.tendwa.zobbackend.discovery.services;

import com.tendwa.zobbackend.discovery.dtos.requests.DiscoverServiceReq;
import com.tendwa.zobbackend.discovery.entities.AppService;

import java.util.List;

public interface DiscoveryService {
    //* Manual Registration *params
    void registerService(DiscoverServiceReq req);

    //* Services need to call this Endpoint to get Registered
    void discoverServices();

    //* Add new Endpoint to existing Service *params
    void addEndpoint();

    //* Update Service Status *params
    void updateServiceStatus();

    //* Update Endpoint Status *params
    void updateEndpointStatus();

    //* Get All registered Services
    List<AppService> getAllServices();

    //* Aggregate Service Status .... Likely not useful... I need to figure out what to do with this one
    String aggregateStatus(String service_id);

}
