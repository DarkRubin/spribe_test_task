package com.example.spribe_test_task.mapper;

import com.example.spribe_test_task.dto.UnitDto;
import com.example.spribe_test_task.entity.Unit;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UnitMapper {

    @Mapping(target = "price", source = "cost")
    Unit toEntity(UnitDto unitDto);

    UnitDto toDto(Unit unit);
}