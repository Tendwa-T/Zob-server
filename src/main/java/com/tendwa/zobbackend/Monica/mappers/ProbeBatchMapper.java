package com.tendwa.zobbackend.Monica.mappers;

import com.tendwa.zobbackend.Monica.dtos.ProbeBatchDto;
import com.tendwa.zobbackend.Monica.entities.ProbeBatch;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProbeBatchMapper {
    ProbeBatch toEntity(ProbeBatchDto probeBatchDto);

    ProbeBatchDto toDto(ProbeBatch probeBatch);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProbeBatch partialUpdate(ProbeBatchDto probeBatchDto, @MappingTarget ProbeBatch probeBatch);
}