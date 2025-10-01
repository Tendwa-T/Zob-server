package com.tendwa.zobbackend.Monica.mappers;

import com.tendwa.zobbackend.Monica.dtos.CanaryCheckDto;
import com.tendwa.zobbackend.Monica.entities.CanaryCheck;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CanaryCheckMapper {
    @Mapping(source = "serviceServiceId", target = "service.serviceId")
    CanaryCheck toEntity(CanaryCheckDto canaryCheckDto);

    @InheritInverseConfiguration(name = "toEntity")
    CanaryCheckDto toDto(CanaryCheck canaryCheck);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CanaryCheck partialUpdate(CanaryCheckDto canaryCheckDto, @MappingTarget CanaryCheck canaryCheck);
}