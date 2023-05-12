package com.warriors.easyStock.Usuario.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsuarioResponseDTO {
    private String nombre;
    private String documento;
    private String telefono;
    private String direccion;
    private String ciudad;
    private Boolean estado;
}
