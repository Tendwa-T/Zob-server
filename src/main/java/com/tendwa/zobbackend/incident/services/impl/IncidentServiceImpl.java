package com.tendwa.zobbackend.incident.services.impl;

import com.tendwa.zobbackend.generic.enums.IncidentStatus;
import com.tendwa.zobbackend.generic.enums.IncidentUpdatedBy;
import com.tendwa.zobbackend.generic.exceptions.ResourceNotFoundException;
import com.tendwa.zobbackend.incident.dtos.crudDtos.create.CreateIncidentDto;
import com.tendwa.zobbackend.incident.entities.Incident;
import com.tendwa.zobbackend.incident.entities.IncidentUpdate;
import com.tendwa.zobbackend.incident.mappers.IncidentMapper;
import com.tendwa.zobbackend.incident.repositories.IncidentRepository;
import com.tendwa.zobbackend.incident.repositories.IncidentUpdateRepository;
import com.tendwa.zobbackend.incident.services.IncidentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class IncidentServiceImpl implements IncidentService {
    private final IncidentRepository incidentRepository;
    private final IncidentMapper incidentMapper;
    private final IncidentUpdateRepository incidentUpdateRepository;

    @Override
    public UUID createIncident(CreateIncidentDto incidentDto) {
        //? Check if there is an incident with the service_id and endpoint_id
        List<Incident> existingIncidents = incidentRepository.findByService_ServiceIdAndEndpoint_EndpointId(incidentDto.getServiceServiceId(), incidentDto.getEndpointEndpointId());

        //? Map the incidents and find the one that is open and update its information
        for(Incident incident : existingIncidents) {
            if(incident.isOpen()){
                incidentMapper.partialUpdate(incidentDto, incident);
                incidentRepository.save(incident);
                log.info("Found an open incident... Updated its info");
                return incident.getIncidentId();
            }
        }

        //? If there is no open incident, create the incident
        Incident newIncident = incidentMapper.toEntity(incidentDto);
        incidentRepository.save(newIncident);
        log.info("Created an open incident...");
        return newIncident.getIncidentId();
    }

    @Override
    public boolean updateIncidentStatus(UUID incident_id, IncidentStatus incidentStatus, String message, IncidentUpdatedBy updatedBy) {
        //? Auto log the updatedBy user when auth is implemented
        //? Find an incident by id
        Incident existingIncident = incidentRepository.findById(incident_id).orElseThrow(
                ()-> new ResourceNotFoundException("Incident","Incident ID", incident_id.toString())
        );

        //? if it exists and is already closed, we cannot update it\'s status
        if (!existingIncident.isOpen()){
            log.info("Update Status: Closed Incident ID {}. Cannot Edit Status", incident_id);
            return false;
        }

        //? update its Incident Status
        existingIncident.setStatus(incidentStatus);
        if (incidentStatus.equals(IncidentStatus.RESOLVED)) existingIncident.setResolvedAt(Instant.now());
        incidentRepository.save(existingIncident);

            //Incident Update insert
        IncidentUpdate newIncidentUpdate = new IncidentUpdate();
        newIncidentUpdate.setIncident(existingIncident);
        newIncidentUpdate.setStatus(incidentStatus);
        newIncidentUpdate.setMessage(message);
        newIncidentUpdate.setUpdatedBy(updatedBy);

        incidentUpdateRepository.save(newIncidentUpdate);
        log.info("Updated Incident Status: Closed Incident ID {}", incident_id);
        log.info("Incident Update: updateID {}", newIncidentUpdate.getUpdateId().toString());
        return true;
    }

    @Override
    public void autoResolveIncident(UUID service_id, UUID endpoint_id) {
        //? Find by Service and Endpoint
        Incident existingIncident = incidentRepository.findByService_ServiceIdAndEndpoint_EndpointId(service_id, endpoint_id)
                .stream()
                .findFirst()
                .orElse(null);
        if (existingIncident == null) {
            log.info("Cannot find incident with Service ID: {}, Endpoint ID: {}",  service_id, endpoint_id);
            return;
        }
        existingIncident.setStatus(IncidentStatus.RESOLVED);
        existingIncident.setResolvedAt(Instant.now());
        incidentRepository.save(existingIncident);

        IncidentUpdate newIncidentUpdate = new IncidentUpdate();
        newIncidentUpdate.setIncident(existingIncident);
        newIncidentUpdate.setStatus(IncidentStatus.RESOLVED);
        newIncidentUpdate.setMessage("AutoResolved Incident: ID " + existingIncident.getIncidentId().toString());
        newIncidentUpdate.setUpdatedBy(IncidentUpdatedBy.M_SYS);
        incidentUpdateRepository.save(newIncidentUpdate);

        log.info("AutoResolved Incident: ID {}", existingIncident.getIncidentId().toString());
    }

    @Override
    public List<Incident> getAllActiveIncidents(UUID service_id) {
        return incidentRepository.getActiveIncidents(service_id);
    }

    @Override
    public List<Incident> getAllIncidents(UUID service_id, Pageable pageable) {
        return incidentRepository.findAllByService_ServiceId(service_id, pageable);
    }

    @Override
    public List<Incident> getIncidentHistory(UUID service_id, Pageable pageable) {
        return incidentRepository.findAllByService_ServiceId(service_id, pageable);
    }


    @Override
    public boolean resolveIncident(UUID incident_id, String message, IncidentUpdatedBy updatedBy) {
        Incident existingIncident = incidentRepository.findById(incident_id).orElseThrow(
                () -> new ResourceNotFoundException("Incident","Incident ID", incident_id.toString())
        );
        if (existingIncident.isResolved() || existingIncident.isClosed()){
            log.info("Cannot resolve Incident ID{}. It has already been resolved", incident_id);
            return false;
        }
        existingIncident.setStatus(IncidentStatus.RESOLVED);
        existingIncident.setResolvedAt(Instant.now());
        incidentRepository.save(existingIncident);

        IncidentUpdate newIncidentUpdate = new IncidentUpdate();
        newIncidentUpdate.setIncident(existingIncident);
        newIncidentUpdate.setStatus(IncidentStatus.RESOLVED);
        newIncidentUpdate.setMessage(message);
        newIncidentUpdate.setUpdatedBy(updatedBy);
        incidentUpdateRepository.save(newIncidentUpdate);

        log.info("Resolved Incident: ID {}", existingIncident.getIncidentId().toString());
        return true;
    }

    @Override
    public boolean closeIncident(UUID incident_id, String message, IncidentUpdatedBy updatedBy) {
        Incident existingIncident = incidentRepository.findById(incident_id).orElseThrow(
                () -> new ResourceNotFoundException("Incident","Incident ID", incident_id.toString())
        );
        if (existingIncident.isClosed()){
            log.info("Cannot close incident ID{}. It has already been closed", incident_id);
            return false;
        }
        existingIncident.setStatus(IncidentStatus.CLOSED);
        incidentRepository.save(existingIncident);

        IncidentUpdate newIncidentUpdate = new IncidentUpdate();
        newIncidentUpdate.setIncident(existingIncident);
        newIncidentUpdate.setStatus(IncidentStatus.CLOSED);
        newIncidentUpdate.setMessage(message);
        newIncidentUpdate.setUpdatedBy(updatedBy);
        incidentUpdateRepository.save(newIncidentUpdate);
        log.info("Closed Incident: ID {}", existingIncident.getIncidentId().toString());

        return true;
    }
}
