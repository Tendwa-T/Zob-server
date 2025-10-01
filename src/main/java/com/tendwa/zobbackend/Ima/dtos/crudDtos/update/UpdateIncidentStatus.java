package com.tendwa.zobbackend.Ima.dtos.crudDtos.update;

import com.tendwa.zobbackend.generic.enums.IncidentStatus;
import com.tendwa.zobbackend.generic.enums.IncidentUpdatedBy;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateIncidentStatus {
    public UUID incident_id;
    public IncidentStatus incident_status;
    public String message;
    public IncidentUpdatedBy updated_by;
}
