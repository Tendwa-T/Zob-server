package com.tendwa.zobbackend.Regina.mappers;

import com.tendwa.zobbackend.Regina.dtos.EndpointDto;
import com.tendwa.zobbackend.Regina.entities.Endpoint;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EndpointMapper {
    Endpoint toEntity(EndpointDto endpointDto);

    EndpointDto toDto(Endpoint endpoint);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Endpoint partialUpdate(EndpointDto endpointDto, @MappingTarget Endpoint endpoint);
}