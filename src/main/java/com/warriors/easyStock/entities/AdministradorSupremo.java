package com.warriors.easyStock.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "administrador_supremo")
public class AdministradorSupremo {
    @Id
    @Column(name = "id_administrador", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "\"user\"")
    private String user;

    @Size(max = 255)
    @Column(name = "password")
    private String password;

}