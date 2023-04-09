package com.warriors.easyStock.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "movimiento_producto")
public class MovimientoProducto {
    @Id
    @Column(name = "movimiento_id_producto")
    private Integer movimientoIdProducto;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producto_id_producto", nullable = false)
    private Producto productoIdProducto;

}