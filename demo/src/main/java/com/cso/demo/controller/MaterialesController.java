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

import com.cso.demo.dto.MaterialesRequestDTO;
import com.cso.demo.dto.MaterialesResponseDTO;
import com.cso.demo.service.MaterialesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/materiales")
@Tag(name = "Materiales", description = "Endpoints para gestionar los materiales del sistema")
public class MaterialesController {

    @Autowired
    private MaterialesService materialesService;

    @PostMapping
    @Operation(
        summary = "Registrar un nuevo material",
        description = "Crea un nuevo material con nombre, estado, cantidad, prestado y mentor asignado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Material creado correctamente",
            content = @Content(schema = @Schema(implementation = MaterialesResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<MaterialesResponseDTO> crear(
            @RequestBody
            @Parameter(description = "Datos del nuevo material a registrar")
            MaterialesRequestDTO materialesRequestDTO) {
        MaterialesResponseDTO materialCreado = materialesService.registrar(materialesRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(materialCreado);
    }

    @GetMapping
    @Operation(
        summary = "Listar todos los materiales",
        description = "Retorna una lista de todos los materiales registrados junto con su mentor asignado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
            content = @Content(schema = @Schema(implementation = MaterialesResponseDTO.class)))
    })
    public ResponseEntity<List<MaterialesResponseDTO>> obtenerTodos() {
        List<MaterialesResponseDTO> materiales = materialesService.obtenerTodos();
        return ResponseEntity.ok(materiales);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar un material por su ID",
        description = "Retorna los datos de un material específico según su ID. " +
                      "Devuelve 404 si el material no existe."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Material encontrado",
            content = @Content(schema = @Schema(implementation = MaterialesResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Material no encontrado")
    })
    public ResponseEntity<MaterialesResponseDTO> obtenerPorId(
            @PathVariable
            @Parameter(description = "ID numérico del material", example = "1")
            Integer id) {
        MaterialesResponseDTO material = materialesService.obtenerPorId(id);
        return ResponseEntity.ok(material);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar un material",
        description = "Modifica los datos de un material existente por su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Material actualizado correctamente",
            content = @Content(schema = @Schema(implementation = MaterialesResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Material no encontrado")
    })
    public ResponseEntity<MaterialesResponseDTO> actualizar(
            @PathVariable
            @Parameter(description = "ID numérico del material", example = "1")
            Integer id,
            @RequestBody
            @Parameter(description = "Nuevos datos del material")
            MaterialesRequestDTO materialesRequestDTO) {
        MaterialesResponseDTO materialActualizado = materialesService.actualizar(id, materialesRequestDTO);
        return ResponseEntity.ok(materialActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un material",
        description = "Elimina permanentemente un material del sistema por su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Material eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Material no encontrado")
    })
    public ResponseEntity<String> eliminar(
            @PathVariable
            @Parameter(description = "ID numérico del material", example = "1")
            Integer id) {
        materialesService.eliminar(id);
        return ResponseEntity.ok("Material eliminado correctamente.");
    }
}