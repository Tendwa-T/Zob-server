package com.tendwa.zobbackend.incident.controllers;

import com.tendwa.zobbackend.generic.responses.ApiResponse;
import com.tendwa.zobbackend.incident.dtos.crudDtos.create.CreateIncidentDto;
import com.tendwa.zobbackend.incident.dtos.crudDtos.update.ResolveIncidentReq;
import com.tendwa.zobbackend.incident.dtos.crudDtos.update.UpdateIncidentStatus;
import com.tendwa.zobbackend.incident.entities.Incident;
import com.tendwa.zobbackend.incident.services.IncidentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name="Incident Service", description = "APIs that target the Incident service")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/incident")
public class IncidentController {

    private final IncidentService incidentService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UUID>> createIncident(@RequestBody CreateIncidentDto req) {
        return ResponseEntity.ok(
                ApiResponse.<UUID>builder()
                        .data(incidentService.createIncident(req))
                        .message("Successfully created incident")
                        .build()
        );
    }

    @PatchMapping("/status/update")
    public ResponseEntity<ApiResponse<Boolean>> updateIncidentStatus(
            @RequestBody UpdateIncidentStatus req
            ){
        return ResponseEntity.ok(
                ApiResponse.<Boolean>builder()
                        .data(incidentService.updateIncidentStatus(
                                req.getIncident_id(),
                                req.getIncident_status(),
                                req.getMessage(),
                                req.getUpdated_by()
                        ))
                        .message("Successfully updated incident")
                        .build()
        );
    }

    @GetMapping("/incidents/active/{serviceID}")
    public ResponseEntity<ApiResponse<List<Incident>>> getActiveIncidents(
            @PathVariable("serviceID") UUID serviceID
    ){
        return ResponseEntity.ok(
                ApiResponse.<List<Incident>>builder()
                        .data(incidentService.getAllActiveIncidents(serviceID))
                        .message("Successfully retrieved active incidents")
                        .build()
        );
    }

    @GetMapping("/history/{serviceID}")
    public ResponseEntity<ApiResponse<List<Incident>>> getIncidentHistory(
            @PathVariable("serviceID") UUID serviceID,
            @PageableDefault @ParameterObject Pageable pageable
    ){
        return ResponseEntity.ok(
                ApiResponse.<List<Incident>>builder()
                        .data(incidentService.getIncidentHistory(serviceID, pageable))
                        .message("Successfully retrieved incidents")
                        .build()
        );
    }

    @PatchMapping("/resolve")
    public ResponseEntity<ApiResponse<Boolean>> resolveIncident(
            @RequestBody ResolveIncidentReq req
            ){
        Boolean resolved = incidentService.resolveIncident(req.getIncident_id(), req.getMessage(), req.getUpdatedBy());
        return ResponseEntity.ok(
                ApiResponse.<Boolean>builder()
                        .data(resolved)
                        .message(resolved?"Successfully resolved incident":"Failed to resolve incident")
                        .build()
        );
    }

    @PatchMapping("/close")
    public ResponseEntity<ApiResponse<Boolean>> closeIncident(
            @RequestBody ResolveIncidentReq req
    ){
        Boolean closed = incidentService.closeIncident(req.getIncident_id(), req.getMessage(), req.getUpdatedBy());
        return ResponseEntity.ok(
                ApiResponse.<Boolean>builder()
                        .data(closed)
                        .message(closed?"Successfully closed incident":"Failed to close incident")
                        .build()
        );
    }


}















