package com.tendwa.zobbackend.incident.mappers;

import com.tendwa.zobbackend.incident.dtos.crudDtos.create.CreateIncidentDto;
import com.tendwa.zobbackend.incident.entities.Incident;
import com.tendwa.zobbackend.incident.dtos.IncidentDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IncidentMapper {
    Incident toEntity(IncidentDto incidentDto);

    IncidentDto toIncidentDto(Incident incident);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Incident partialUpdate(IncidentDto incidentDto, @MappingTarget Incident incident);

    @Mapping(source = "endpointEndpointId", target = "endpoint.endpointId")
    @Mapping(source = "serviceServiceId", target = "service.serviceId")
    @Mapping(source = "incidentId", target = "incidentId", ignore = true)
    Incident toEntity(CreateIncidentDto createIncidentDto);

    @InheritInverseConfiguration(name = "toEntity")
    CreateIncidentDto toCreateIncidentDto(Incident incident);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Incident partialUpdate(CreateIncidentDto createIncidentDto, @MappingTarget Incident incident);

}