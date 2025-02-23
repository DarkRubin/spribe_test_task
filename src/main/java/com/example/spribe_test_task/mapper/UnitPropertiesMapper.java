package com.example.spribe_test_task.mapper;

import com.example.spribe_test_task.dto.UnitPropertiesDto;
import com.example.spribe_test_task.entity.UnitProperties;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UnitPropertiesMapper {
    UnitProperties toEntity(UnitPropertiesDto unitPropertiesDto);

    UnitPropertiesDto toDto(UnitProperties unitProperties);
}