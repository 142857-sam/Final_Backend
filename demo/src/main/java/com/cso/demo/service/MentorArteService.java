package com.cso.demo.service;

import lombok.NonNull;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cso.demo.dto.MentorArteRequestDTO;
import com.cso.demo.dto.MentorArteResponseDTO;
import com.cso.demo.exception.RecursoNoEncontradoException;
import com.cso.demo.mapper.MentorArteMapper;
import com.cso.demo.model.MentorArte;
import com.cso.demo.repository.MentorArteRepository;

@Service
public class MentorArteService {

    @Autowired
    private MentorArteMapper mentorArteMapper;

    @Autowired
    private MentorArteRepository mentorArteRepository;

    public MentorArteResponseDTO registrar(@NonNull MentorArteRequestDTO dto) {
        validarDatosMentor(dto);

        MentorArte mentor = mentorArteMapper.toMentorArte(dto);

        @SuppressWarnings("null")
        MentorArte mentorGuardado = mentorArteRepository.save(mentor);

        return mentorArteMapper.toResponseDTO(mentorGuardado);
    }

    public List<MentorArteResponseDTO> obtenerTodos() {
        return mentorArteRepository.findAll()
                .stream()
                .map(mentorArteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public MentorArteResponseDTO obtenerPorId(@NonNull Integer id) {
        MentorArte mentor = mentorArteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Mentor no encontrado con ID: " + id));

        return mentorArteMapper.toResponseDTO(mentor);
    }

    @SuppressWarnings("null")
    public MentorArteResponseDTO actualizar(@NonNull Integer id, @NonNull MentorArteRequestDTO dto) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del mentor debe ser mayor que 0.");
        }

        validarDatosMentor(dto);

        MentorArte mentorExistente = mentorArteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Mentor no encontrado con ID: " + id));

        mentorArteMapper.actualizarEntidadDesdeDto(dto, mentorExistente);

        MentorArte mentorActualizado = mentorArteRepository.save(mentorExistente);

        return mentorArteMapper.toResponseDTO(mentorActualizado);
    }

    public void eliminar(@NonNull Integer id) {
        if (!mentorArteRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se puede eliminar, mentor no encontrado con ID: " + id);
        }

        mentorArteRepository.deleteById(id);
    }

    private void validarDatosMentor(MentorArteRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Los datos del mentor son obligatorios.");
        }

        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        if (dto.getApellido() == null || dto.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }

        if (dto.getSalon() == null || dto.getSalon().trim().isEmpty()) {
            throw new IllegalArgumentException("El salón es obligatorio.");
        }
    }
}