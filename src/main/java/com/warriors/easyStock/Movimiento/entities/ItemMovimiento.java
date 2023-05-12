package com.warriors.easyStock.Movimiento.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("valor_item_movimiento")
    @Column(name = "valor_item_movimiento")
    private Double valorItemMovimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto producto;

    @JsonProperty("producto_cantidad_anterior")
    @Column(name = "producto_cantidad_anterior")
    private int productoCantidadAnterior;

    @JsonProperty("producto_cantidad_actual")
    @Column(name = "producto_cantidad_actual")
    private int productoCantidadActual;

    public Double calcularImporteVenta() {
        return (double) cantidad * producto.getValorVenta();
    }
    public Double calcularImporteCompra() {
        return (double) cantidad * producto.getValorCompra();
    }

    private static final Long serialVersionUID = 1L;
}
