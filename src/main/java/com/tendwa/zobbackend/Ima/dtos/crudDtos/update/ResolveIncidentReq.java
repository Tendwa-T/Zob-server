package com.tendwa.zobbackend.Ima.dtos.crudDtos.update;

import com.tendwa.zobbackend.generic.enums.IncidentUpdatedBy;
import lombok.Data;

import java.util.UUID;

@Data
public class ResolveIncidentReq {
    private UUID incident_id;
    private String message;
    private IncidentUpdatedBy updatedBy;
}
