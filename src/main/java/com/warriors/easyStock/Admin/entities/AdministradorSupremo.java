package com.warriors.easyStock.Admin.entities;

import lombok.*;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255)
    @Column(name = "\"user\"")
    private String user;

    @Size(max = 255)
    @Column(name = "password")
    private String password;

}