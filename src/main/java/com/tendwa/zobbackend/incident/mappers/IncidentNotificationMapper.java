package com.tendwa.zobbackend.incident.mappers;

import com.tendwa.zobbackend.incident.dtos.crudDtos.create.CreateIncidentNotificationDto;
import com.tendwa.zobbackend.incident.entities.IncidentNotification;
import com.tendwa.zobbackend.incident.dtos.IncidentNotificationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {IncidentMapper.class})
public interface IncidentNotificationMapper {
    IncidentNotification toEntity(IncidentNotificationDto incidentNotificationDto);

    IncidentNotificationDto toDto(IncidentNotification incidentNotification);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    IncidentNotification partialUpdate(IncidentNotificationDto incidentNotificationDto, @MappingTarget IncidentNotification incidentNotification);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    IncidentNotification partialUpdate(CreateIncidentNotificationDto createIncidentNotificationDto, @MappingTarget IncidentNotification incidentNotification);

    @Mapping(source = "incidentIncidentId", target = "incident.incidentId")
    IncidentNotification toEntity(CreateIncidentNotificationDto createIncidentNotificationDto);

    @Mapping(source = "incident.incidentId", target = "incidentIncidentId")
    CreateIncidentNotificationDto toCreatedIncidentNotification(IncidentNotification incidentNotification);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "incidentIncidentId", target = "incident.incidentId")
    IncidentNotification partialUpdate(@MappingTarget IncidentNotification incidentNotification, CreateIncidentNotificationDto createIncidentNotificationDto);
}