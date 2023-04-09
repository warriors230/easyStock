package com.warriors.easyStock.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "id_cliente", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Size(max = 20)
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Size(max = 100)
    @Column(name = "direccion", length = 100)
    private String direccion;

    @Size(max = 100)
    @Column(name = "ciudad", length = 100)
    private String ciudad;

    @Size(max = 100)
    @Column(name = "barrio", length = 100)
    private String barrio;

}