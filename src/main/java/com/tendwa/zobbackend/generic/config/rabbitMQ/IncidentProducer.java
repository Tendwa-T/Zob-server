package com.tendwa.zobbackend.generic.config.rabbitMQ;

import com.tendwa.zobbackend.discovery.dtos.requests.DiscoverServiceReq;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class IncidentProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendIncidentMessage(DiscoverServiceReq serviceDataJson){
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                serviceDataJson
        );
        log.info("Incident message sent: {} ", serviceDataJson.getName());
    }
}
