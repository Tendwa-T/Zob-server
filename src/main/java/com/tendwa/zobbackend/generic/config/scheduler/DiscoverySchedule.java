package com.tendwa.zobbackend.generic.config.scheduler;


import com.tendwa.zobbackend.discovery.services.DiscoveryService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DiscoverySchedule {
    private final DiscoveryService discoveryService;

    @Scheduled(fixedDelay = 10000)
    public void servicePollDiscovery(){
        // discoveryService.discoverServices();
        System.out.println("Service polling started");
    }

}
