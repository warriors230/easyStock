package com.warriors.easyStock.Producto.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto implements Serializable {
    @Id
    @Column(name = "id_producto", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Size(max = 8)
    @NotNull
    @Column(name = "unidad_medida", nullable = false, length = 8)
    private String unidadMedida;

    @NotNull
    @Column(name = "valor_compra", nullable = false)
    private Double valorCompra;

    @NotNull
    @Column(name = "valor_venta", nullable = false)
    private Double valorVenta;

    @Column(name = "ganancia")
    private Double ganancia;

    @NotNull
    @Column(name = "estado", nullable = false)
    private Boolean estado = false;

    @NotNull
    @Column(name = "cantidad_stock", nullable = false)
    private Double cantidadStock;

}