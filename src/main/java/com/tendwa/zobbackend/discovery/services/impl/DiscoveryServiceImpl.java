package com.tendwa.zobbackend.discovery.services.impl;

import com.tendwa.zobbackend.discovery.dtos.requests.*;
import com.tendwa.zobbackend.discovery.entities.AppService;
import com.tendwa.zobbackend.discovery.entities.Endpoint;
import com.tendwa.zobbackend.discovery.repositories.EndpointRepository;
import com.tendwa.zobbackend.discovery.repositories.ServiceRepository;
import com.tendwa.zobbackend.discovery.services.DiscoveryService;
import com.tendwa.zobbackend.generic.enums.AppServiceStatus;
import com.tendwa.zobbackend.generic.enums.EndpointType;
import com.tendwa.zobbackend.generic.enums.HttpMethods;
import com.tendwa.zobbackend.generic.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
public class DiscoveryServiceImpl implements DiscoveryService {
    private final ServiceRepository serviceRepository;
    private final EndpointRepository endpointRepository;

    @Override
    @Transactional
    public void registerService(DiscoverServiceReq req) {
        AppService existingAppService = serviceRepository.findAllByNameAndBaseUrl(req.getName(), req.getBase_url());

        if(existingAppService != null && existingAppService.getStatus() != AppServiceStatus.UP) {
            existingAppService.setStatus(AppServiceStatus.UP);
            serviceRepository.save(existingAppService);
            return;
        }

        AppService newAppService = new AppService();
        newAppService.setName(req.getName());
        newAppService.setBaseUrl(req.getBase_url());
        newAppService.setAuthToken(req.getAuth_token());
        newAppService.setStatus(AppServiceStatus.UP);
        serviceRepository.save(newAppService);

        for (EndpointMap endpoint : req.getEndpoints()) {
            Endpoint newEndpoint = new Endpoint();
            newEndpoint.setAppService(newAppService);
            newEndpoint.setPath(endpoint.getEndpointPath());
            newEndpoint.setMethod(HttpMethods.valueOf(endpoint.getMethod()));
            newEndpoint.setType(EndpointType.valueOf(endpoint.getEndpointType()));
            newEndpoint.setStatus(AppServiceStatus.UNKNOWN);
            endpointRepository.save(newEndpoint);
        }

        log.info("New service {} has been registered", newAppService.getName());
    }

    @Override
    @Transactional
    // TODO: Change the functionality of this to only check the health of the service
    // - So something like callExternalEndpoint(http:... , service.getBaseUrl(), "/health")
    public void discoverServices() {
        // Get all the services
        List<AppService> services = getAllServices();

        // Loop through the services to call the endpoint canaries
        for(AppService service : services) {
            for (Endpoint endpoint : service.getEndpoints()) {
                if(endpoint.getType() != EndpointType.CANARY) {
                    callExternalEndpoint("http://localhost:8081" , service.getBaseUrl(), endpoint);
                }
            }
        }
    }

    @Override
    public void addEndpoint(EndpointsReq req) {
        AppService existingService = serviceRepository.findById(UUID.fromString(req.getAppService_id())).orElseThrow(
                ()->new ResourceNotFoundException("Service not found")
        );

        EndpointType reqType = EndpointType.NORMAL;
        HttpMethods reqMethod = HttpMethods.GET;

        switch (req.getEndpointType()){
            case "CANARY":
                reqType = EndpointType.CANARY;
                break;
            case "NORMAL":
                break;
        }

        switch (req.getMethod()) {
            case "GET":
                break;
            case "POST":
                reqMethod = HttpMethods.POST;
                break;
            case "PUT":
                reqMethod = HttpMethods.PUT;
                break;
            case "DELETE":
                reqMethod = HttpMethods.DELETE;
                break;
            case "PATCH":
                reqMethod = HttpMethods.PATCH;
                break;
        }

        Endpoint newEndpoint =  new Endpoint();
        newEndpoint.setAppService(existingService);
        newEndpoint.setPath(req.getPath());
        newEndpoint.setType(reqType);
        newEndpoint.setMethod(reqMethod);

        endpointRepository.save(newEndpoint);
        log.info("New endpoint {} has been registered", newEndpoint.getPath());
    }

    @Override
    public void updateServiceStatus(ServiceStatusReq req) {
        AppService existingService = serviceRepository.findById(UUID.fromString(req.getAppService_id())).orElseThrow(
                ()->new ResourceNotFoundException("Service not found")
        );
        AppServiceStatus serviceStatus = AppServiceStatus.UNKNOWN;

        serviceStatus = switch (req.getStatus()) {
            case "UP" -> AppServiceStatus.UP;
            case "DOWN" -> AppServiceStatus.DOWN;
            default -> serviceStatus;
        };


        existingService.setStatus(serviceStatus);
        serviceRepository.save(existingService);
        log.info("Service {} has been updated. New status: {}", existingService.getName(),  existingService.getStatus());
    }

    @Override
    public void updateEndpointStatus(EndpointStatusReq req) {
        Endpoint existingEndpoint = endpointRepository.findById(UUID.fromString(req.getEndpointId())).orElseThrow(
                ()->new ResourceNotFoundException("Endpoint not found")
        );

        AppServiceStatus serviceStatus = AppServiceStatus.UNKNOWN;

        serviceStatus = switch (req.getStatus()){
            case "UP" -> AppServiceStatus.UP;
            case "DOWN" -> AppServiceStatus.DOWN;
            default -> serviceStatus;
        };
        existingEndpoint.setStatus(serviceStatus);
        endpointRepository.save(existingEndpoint);
        log.info("Endpoint {} has been updated. New Status: {}", existingEndpoint.getPath(),  existingEndpoint.getStatus());
    }

    @Override
    public List<AppService> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public String aggregateStatus(String service_id) {
        List<Endpoint> endpoints = endpointRepository.findAllByAppService_ServiceId(UUID.fromString(service_id));

        boolean hasDown = endpoints.stream()
                .anyMatch(ep -> ep.getStatus().equals(AppServiceStatus.DOWN));

        return hasDown ? AppServiceStatus.DOWN.name() : AppServiceStatus.UP.name();
    }


    private void callExternalEndpoint(String hostUrl, String baseUrl, Endpoint endpoint) {
        WebClient client = WebClient.create(hostUrl+"/"+baseUrl);

        client.get()
                .uri(endpoint.getPath())
                .exchangeToMono(response->{
                    HttpStatusCode status = response.statusCode();
                    if(status.is2xxSuccessful()) {
                        endpoint.setStatus(AppServiceStatus.UP);
                    } else  {
                        endpoint.setStatus(AppServiceStatus.DOWN);
                    }
                    return response.bodyToMono(String.class).map(body->{
                        log.info("Response from external endpoint {} is {}", endpoint.getPath(), body);
                        return body;
                    });
                }).subscribe();

    }

}












