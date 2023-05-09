package com.warriors.easyStock.Movimiento.entities;

import com.warriors.easyStock.Producto.entities.Producto;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movimientoIdProducto;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "producto_id_producto", nullable = false)
    private Producto productoIdProducto;

}