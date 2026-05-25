package com.cso.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cso.demo.dto.MentorArteRequestDTO;
import com.cso.demo.dto.MentorArteResponseDTO;
import com.cso.demo.service.MentorArteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/mentores")
@Tag(name = "Mentores de Arte", description = "Endpoints para gestionar los mentores de arte")
public class MentorArteController {

    @Autowired
    private MentorArteService mentorArteService;

    @PostMapping
    @Operation(
        summary = "Registrar un nuevo mentor",
        description = "Crea un nuevo mentor de arte con nombre, apellido y salón."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Mentor creado correctamente",
            content = @Content(schema = @Schema(implementation = MentorArteResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<MentorArteResponseDTO> crear(
            @RequestBody
            @Parameter(description = "Datos del nuevo mentor a registrar")
            MentorArteRequestDTO mentorArteRequestDTO) {
        MentorArteResponseDTO mentorCreado = mentorArteService.registrar(mentorArteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mentorCreado);
    }

    @GetMapping
    @Operation(
        summary = "Listar todos los mentores",
        description = "Retorna una lista de todos los mentores de arte registrados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
            content = @Content(schema = @Schema(implementation = MentorArteResponseDTO.class)))
    })
    public ResponseEntity<List<MentorArteResponseDTO>> obtenerTodos() {
        List<MentorArteResponseDTO> mentores = mentorArteService.obtenerTodos();
        return ResponseEntity.ok(mentores);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar un mentor por su ID",
        description = "Retorna los datos de un mentor específico según su ID. " +
                      "Devuelve 404 si el mentor no existe."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mentor encontrado",
            content = @Content(schema = @Schema(implementation = MentorArteResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Mentor no encontrado")
    })
    public ResponseEntity<MentorArteResponseDTO> obtenerPorId(
            @PathVariable
            @Parameter(description = "ID numérico del mentor", example = "1")
            Integer id) {
        MentorArteResponseDTO mentor = mentorArteService.obtenerPorId(id);
        return ResponseEntity.ok(mentor);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar un mentor",
        description = "Modifica los datos de un mentor existente por su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mentor actualizado correctamente",
            content = @Content(schema = @Schema(implementation = MentorArteResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Mentor no encontrado")
    })
    public ResponseEntity<MentorArteResponseDTO> actualizar(
            @PathVariable
            @Parameter(description = "ID numérico del mentor", example = "1")
            Integer id,
            @RequestBody
            @Parameter(description = "Nuevos datos del mentor")
            MentorArteRequestDTO mentorArteRequestDTO) {
        MentorArteResponseDTO mentorActualizado = mentorArteService.actualizar(id, mentorArteRequestDTO);
        return ResponseEntity.ok(mentorActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un mentor",
        description = "Elimina permanentemente un mentor del sistema por su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mentor eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Mentor no encontrado")
    })
    public ResponseEntity<String> eliminar(
            @PathVariable
            @Parameter(description = "ID numérico del mentor", example = "1")
            Integer id) {
        mentorArteService.eliminar(id);
        return ResponseEntity.ok("Mentor eliminado correctamente.");
    }
}