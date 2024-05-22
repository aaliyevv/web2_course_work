package com.example.subway.mapper;

import com.example.subway.entity.Subway;
import com.example.subway.entity.dto.SubwayDTO;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
@Component
public interface SubwayMapper {

    SubwayMapper INSTANCE = Mappers.getMapper(SubwayMapper.class);

    @Mapping(target = "id", source = "id")
    SubwayDTO subwayToSubwayDTO(Subway subway);

    @Mapping(target = "id", source = "id")
    Subway subwayDTOToSubway(SubwayDTO subwayDTO);

}
