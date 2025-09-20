package com.tendwa.zobbackend.generic.config.rabbitMQ;

import com.tendwa.zobbackend.discovery.dtos.requests.DiscoverServiceReq;
import com.tendwa.zobbackend.discovery.services.DiscoveryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class IncidentConsumer {

    private DiscoveryService discoveryService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveMessage(DiscoverServiceReq req) {
        log.info("Incident message received: {}", req.getName());
        //TODO: I am supposed to Deserialize the JSON here
        discoveryService.registerService(req);
    }


}
