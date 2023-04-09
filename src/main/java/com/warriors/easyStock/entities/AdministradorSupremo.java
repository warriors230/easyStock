package com.warriors.easyStock.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "administrador_supremo")
public class AdministradorSupremo implements Serializable {
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