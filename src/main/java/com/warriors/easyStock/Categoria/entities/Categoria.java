package com.warriors.easyStock.Categoria.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categorias")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id_categoria")
    private int idCategoria;
    private String nombre;
}
