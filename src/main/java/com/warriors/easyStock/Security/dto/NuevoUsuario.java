package com.warriors.easyStock.Security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NuevoUsuario {

    private String nombre;

    private String documento;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String usuario;
    private String contrasena;
    private Boolean estado;
    private String correo;
    private Set<String> roles = new HashSet<>();
}
