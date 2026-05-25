package com.cso.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.cso.demo.dto.MentorArteRequestDTO;
import com.cso.demo.dto.MentorArteResponseDTO;
import com.cso.demo.model.MentorArte;

@Mapper(componentModel = "spring")
public interface MentorArteMapper {

    MentorArteResponseDTO toResponseDTO(MentorArte mentorArte);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "materiales", ignore = true)
    MentorArte toMentorArte(MentorArteRequestDTO mentorArteRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "materiales", ignore = true)
    void actualizarEntidadDesdeDto(MentorArteRequestDTO dto, @MappingTarget MentorArte entidad);
}