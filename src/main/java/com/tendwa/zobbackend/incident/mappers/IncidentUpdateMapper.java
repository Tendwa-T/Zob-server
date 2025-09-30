package com.tendwa.zobbackend.incident.mappers;

import com.tendwa.zobbackend.incident.dtos.crudDtos.create.CreateIncidentUpdateDto;
import com.tendwa.zobbackend.incident.entities.IncidentUpdate;
import com.tendwa.zobbackend.incident.dtos.IncidentUpdateDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IncidentUpdateMapper {
    IncidentUpdate toEntity(IncidentUpdateDto incidentUpdateDto);

    IncidentUpdateDto toDto(IncidentUpdate incidentUpdate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    IncidentUpdate partialUpdate(IncidentUpdateDto incidentUpdateDto, @MappingTarget IncidentUpdate incidentUpdate);

    @Mapping(source = "incidentIncidentId", target = "incident.incidentId")
    IncidentUpdate toEntity(CreateIncidentUpdateDto createIncidentUpdateDto);

    @Mapping(source = "incident.incidentId", target = "incidentIncidentId")
    CreateIncidentUpdateDto toCreateIncidentUpdateDto(IncidentUpdate incidentUpdate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "incidentIncidentId", target = "incident.incidentId")
    IncidentUpdate partialUpdate(CreateIncidentUpdateDto createIncidentUpdateDto, @MappingTarget IncidentUpdate incidentUpdate);
}