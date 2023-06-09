package com.warriors.easyStock.Roles.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.warriors.easyStock.Roles.enums.RolNombre;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    @JsonProperty("id_rol")
    private int idRol;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;
    private static final Long SerialVersionUID = 1L;

    public Rol(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
