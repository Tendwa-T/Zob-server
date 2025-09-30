package com.tendwa.zobbackend.discovery.services;

import com.tendwa.zobbackend.discovery.dtos.requests.DiscoverServiceReq;
import com.tendwa.zobbackend.discovery.dtos.requests.EndpointStatusReq;
import com.tendwa.zobbackend.discovery.dtos.requests.EndpointsReq;
import com.tendwa.zobbackend.discovery.dtos.requests.ServiceStatusReq;
import com.tendwa.zobbackend.discovery.entities.AppService;

import java.util.List;

public interface DiscoveryService {
    //* Manual Registration *params
    void registerService(DiscoverServiceReq req);

    //* Services need to call this Endpoint to get Registered
    void discoverServices();

    //* Add new Endpoint to existing Service *params
    void addEndpoint(EndpointsReq req);

    //* Update Service Status *params
    void updateServiceStatus(ServiceStatusReq req);

    //* Update Endpoint Status *params
    void updateEndpointStatus(EndpointStatusReq req);

    //* Get All registered Services
    List<AppService> getAllServices();

    //* Aggregate Service Status .... Likely not useful... I need to figure out what to do with this one
    String aggregateStatus(String service_id);

}
