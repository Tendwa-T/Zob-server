package com.tendwa.zobbackend.discovery.mappers;

import com.tendwa.zobbackend.discovery.entities.ServiceLog;
import com.tendwa.zobbackend.discovery.dtos.ServiceLogDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceLogMapper {
    ServiceLog toEntity(ServiceLogDto serviceLogDto);

    ServiceLogDto toDto(ServiceLog serviceLog);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ServiceLog partialUpdate(ServiceLogDto serviceLogDto, @MappingTarget ServiceLog serviceLog);
}