package com.cso.demo.dto;

import lombok.Data;

@Data
public class MaterialesResponseDTO {
    private int id;
    private String nombre;
    private String estado;
    private int cantidad;
    private Boolean prestado;
    private MentorArteResponseDTO mentor;
}