package com.warriors.easyStock.Movimiento.entities;

import com.warriors.easyStock.Producto.entities.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_movimientos")
public class ItemMovimiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item", nullable = false)
    private int id;
    private int cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private Producto producto;

    public Double calcularImporte() {
        return (double) cantidad * producto.getValorVenta();
    }
    private static final Long serialVersionUID = 1L;
}
