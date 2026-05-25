package com.cso.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.cso.demo.dto.MaterialesRequestDTO;
import com.cso.demo.dto.MaterialesResponseDTO;
import com.cso.demo.model.Materiales;

@Mapper(componentModel = "spring", uses = MentorArteMapper.class)
public interface MaterialesMapper {

    MaterialesResponseDTO toResponseDTO(Materiales materiales);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mentor", ignore = true)
    Materiales toMateriales(MaterialesRequestDTO materialesRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mentor", ignore = true)
    void actualizarEntidadDesdeDto(MaterialesRequestDTO dto, @MappingTarget Materiales entidad);
}
