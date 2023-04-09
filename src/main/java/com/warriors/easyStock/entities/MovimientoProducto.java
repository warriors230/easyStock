package com.warriors.easyStock.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movimiento_producto")
public class MovimientoProducto implements Serializable {
    @Id
    @Column(name = "movimiento_id_producto")
    private Integer movimientoIdProducto;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producto_id_producto", nullable = false)
    private Producto productoIdProducto;

}