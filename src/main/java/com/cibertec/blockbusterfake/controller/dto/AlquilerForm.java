package com.cibertec.blockbusterfake.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class AlquilerForm {
    private Long clienteId;
    private List<DetalleDTO> detalles;
}
