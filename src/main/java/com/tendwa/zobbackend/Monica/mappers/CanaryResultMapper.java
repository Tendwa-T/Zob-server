package com.tendwa.zobbackend.Monica.mappers;

import com.tendwa.zobbackend.Monica.dtos.CanaryResultDto;
import com.tendwa.zobbackend.Monica.entities.CanaryResult;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CanaryResultMapper {
    @Mapping(source = "canaryCheckCanaryCheckId", target = "canaryCheck.canaryCheckId")
    CanaryResult toEntity(CanaryResultDto canaryResultDto);

    @Mapping(source = "canaryCheck.canaryCheckId", target = "canaryCheckCanaryCheckId")
    CanaryResultDto toDto(CanaryResult canaryResult);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "canaryCheckCanaryCheckId", target = "canaryCheck.canaryCheckId")
    CanaryResult partialUpdate(CanaryResultDto canaryResultDto, @MappingTarget CanaryResult canaryResult);
}