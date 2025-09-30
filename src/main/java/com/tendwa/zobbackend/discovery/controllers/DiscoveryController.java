package com.tendwa.zobbackend.discovery.controllers;


import com.tendwa.zobbackend.discovery.dtos.requests.DiscoverServiceReq;
import com.tendwa.zobbackend.discovery.dtos.requests.EndpointStatusReq;
import com.tendwa.zobbackend.discovery.dtos.requests.EndpointsReq;
import com.tendwa.zobbackend.discovery.dtos.requests.ServiceStatusReq;
import com.tendwa.zobbackend.discovery.services.DiscoveryService;
import com.tendwa.zobbackend.generic.config.rabbitMQ.ServiceRegistrarProducer;
import com.tendwa.zobbackend.generic.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Discovery Service", description = "APIs that target the discovery service")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/discover")
public class DiscoveryController {
    private final ServiceRegistrarProducer serviceRegistrarProducer;
    private DiscoveryService discoveryService;

    @PostMapping("/registerService")
    public ResponseEntity<ApiResponse<Void>> registerService(@RequestBody DiscoverServiceReq req) {
        serviceRegistrarProducer.sendIncidentMessage(req);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .message(String.format("Successfully Scheduled service %s", req.getName()))
                        .build()
        );
    }

    @PostMapping("/endpoint/add")
    public ResponseEntity<ApiResponse<Void>> addEndpoint(@RequestBody EndpointsReq req) {
        discoveryService.addEndpoint(req);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .message(String.format("Endpoint Added %s", req.getPath()))
                        .build()
        );
    }

    @PatchMapping("/service/status")
    public ResponseEntity<ApiResponse<Void>> updateEndpointStatus(@RequestBody ServiceStatusReq req) {
        discoveryService.updateServiceStatus(req);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .message(String.format("Service Status Updated %s", req.getStatus()))
                        .build()
        );
    }

    @PatchMapping("/endpoint/status")
    public ResponseEntity<ApiResponse<Void>> updateEndpointStatus(@RequestBody EndpointStatusReq req) {
        discoveryService.updateEndpointStatus(req);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .message(String.format("Endpoint Status Updated %s", req.getStatus()))
                        .build()
        );
    }

}
