package com.tendwa.zobbackend.Regina.mappers;

import com.tendwa.zobbackend.Regina.entities.AppService;
import com.tendwa.zobbackend.Regina.dtos.ServiceDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceMapper {
    AppService toEntity(ServiceDto serviceDto);

    ServiceDto toDto(AppService appService);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AppService partialUpdate(ServiceDto serviceDto, @MappingTarget AppService appService);
}