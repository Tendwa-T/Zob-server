package com.tendwa.zobbackend.discovery.services.impl;

import com.tendwa.zobbackend.discovery.entities.AppService;
import com.tendwa.zobbackend.discovery.dtos.requests.DiscoverServiceReq;
import com.tendwa.zobbackend.discovery.entities.Endpoint;
import com.tendwa.zobbackend.discovery.repositories.ServiceRepository;
import com.tendwa.zobbackend.discovery.services.DiscoveryService;
import com.tendwa.zobbackend.generic.enums.AppServiceStatus;
import com.tendwa.zobbackend.generic.enums.EndpointType;
import com.tendwa.zobbackend.generic.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class DiscoveryServiceImpl implements DiscoveryService {
    private final ServiceRepository serviceRepository;

    @Override
    public void registerService(DiscoverServiceReq req) {
        AppService existingAppService = serviceRepository.findAllByNameAndBaseUrl(req.getName(), req.getBase_url())
                .orElseThrow(()->new ResourceNotFoundException("Service not found"));

        if(existingAppService.getStatus() != AppServiceStatus.UP) {
            existingAppService.setStatus(AppServiceStatus.UP);
            serviceRepository.save(existingAppService);
        }

        AppService newAppService = new AppService();
        newAppService.setName(req.getName());
        newAppService.setBaseUrl(req.getBase_url());
        newAppService.setAuthToken(req.getAuth_token());
        newAppService.setStatus(AppServiceStatus.UP);
        serviceRepository.save(newAppService);

        log.info("New service {} has been registered", newAppService.getName());
    }

    @Override
    public void discoverServices() {
        // Get all the services
        List<AppService> services = getAllServices();

        // Loop through the services to call the endpoint canaries
        for(AppService service : services) {
            for (Endpoint endpoint : service.getEndpoints()) {
                if(endpoint.getType() != EndpointType.CANARY) {
                    callExternalEndpoint(service.getName(), endpoint);
                }
            }
        }
    }

    @Override
    public void addEndpoint() {

    }

    @Override
    public void updateServiceStatus() {

    }

    @Override
    public void updateEndpointStatus() {

    }

    @Override
    public List<AppService> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public String aggregateStatus(String service_id) {
        return "";
    }


    private void callExternalEndpoint(String baseUrl, Endpoint endpoint) {
        WebClient client = WebClient.create(baseUrl);

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












