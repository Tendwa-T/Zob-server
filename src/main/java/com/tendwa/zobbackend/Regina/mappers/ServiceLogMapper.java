package com.tendwa.zobbackend.Regina.mappers;

import com.tendwa.zobbackend.Regina.entities.ServiceLog;
import com.tendwa.zobbackend.Regina.dtos.ServiceLogDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceLogMapper {
    ServiceLog toEntity(ServiceLogDto serviceLogDto);

    ServiceLogDto toDto(ServiceLog serviceLog);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ServiceLog partialUpdate(ServiceLogDto serviceLogDto, @MappingTarget ServiceLog serviceLog);
}