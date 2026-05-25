package com.cso.demo.dto;

import lombok.Data;

@Data
public class MaterialesRequestDTO {
    private String nombre;
    private String estado;
    private int cantidad;
    private Boolean prestado;
    private int mentorId;
}