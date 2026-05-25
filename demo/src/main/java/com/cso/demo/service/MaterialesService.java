package com.cso.demo.service;

import lombok.NonNull;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cso.demo.dto.MaterialesRequestDTO;
import com.cso.demo.dto.MaterialesResponseDTO;
import com.cso.demo.exception.RecursoNoEncontradoException;
import com.cso.demo.mapper.MaterialesMapper;
import com.cso.demo.model.MentorArte;
import com.cso.demo.model.Materiales;
import com.cso.demo.repository.MaterialesRepository;
import com.cso.demo.repository.MentorArteRepository;

@Service
public class MaterialesService {

    @Autowired
    private MaterialesMapper materialesMapper;

    @Autowired
    private MaterialesRepository materialesRepository;

    @Autowired
    private MentorArteRepository mentorArteRepository;

    public MaterialesResponseDTO registrar(@NonNull MaterialesRequestDTO dto) {
        validarDatosMaterial(dto);

        Integer mentorId = dto.getMentorId();
        if (mentorId == null || mentorId <= 0) {
            throw new IllegalArgumentException("El ID del mentor debe ser válido.");
        }

        MentorArte mentor = mentorArteRepository.findById(mentorId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Mentor no encontrado con ID: " + mentorId));

        Materiales material = materialesMapper.toMateriales(dto);
        material.setMentor(mentor);

        @SuppressWarnings("null")
        Materiales materialGuardado = materialesRepository.save(material);

        return materialesMapper.toResponseDTO(materialGuardado);
    }

    public List<MaterialesResponseDTO> obtenerTodos() {
        return materialesRepository.findAll()
                .stream()
                .map(materialesMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public MaterialesResponseDTO obtenerPorId(@NonNull Integer id) {
        Materiales material = materialesRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Material no encontrado con ID: " + id));

        return materialesMapper.toResponseDTO(material);
    }

    @SuppressWarnings("null")
    public MaterialesResponseDTO actualizar(@NonNull Integer id, @NonNull MaterialesRequestDTO dto) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del material debe ser mayor que 0.");
        }

        validarDatosMaterial(dto);

        Materiales materialExistente = materialesRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Material no encontrado con ID: " + id));

        Integer mentorId = dto.getMentorId();
        if (mentorId != null && mentorId > 0) {
            MentorArte mentor = mentorArteRepository.findById(mentorId)
                    .orElseThrow(() -> new RecursoNoEncontradoException("Mentor no encontrado con ID: " + mentorId));
            materialExistente.setMentor(mentor);
        }

        materialesMapper.actualizarEntidadDesdeDto(dto, materialExistente);

        Materiales materialActualizado = materialesRepository.save(materialExistente);

        return materialesMapper.toResponseDTO(materialActualizado);
    }

    public void eliminar(@NonNull Integer id) {
        if (!materialesRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se puede eliminar, material no encontrado con ID: " + id);
        }

        materialesRepository.deleteById(id);
    }

    private void validarDatosMaterial(MaterialesRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Los datos del material son obligatorios.");
        }

        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        if (dto.getEstado() == null || dto.getEstado().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio.");
        }

        if (dto.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
        }

        if (dto.getPrestado() == null) {
            throw new IllegalArgumentException("El campo prestado es obligatorio.");
        }

        if (dto.getMentorId() == null || dto.getMentorId() <= 0) {
            throw new IllegalArgumentException("El ID del mentor debe ser válido.");
        }
    }
}