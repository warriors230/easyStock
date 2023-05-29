package com.warriors.easyStock.Movimiento.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.warriors.easyStock.Usuario.entities.Usuario;
import com.warriors.easyStock.Utils.constants.ConstantesSistema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movimientos")
public class Movimiento implements Serializable {
    @Id
    @Column(name = "id_movimiento", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "valor_pagado")
    @JsonProperty("valor_pagado")
    private Double valorPagado;
    private Double pendiente;
    private Double cambio;

    @Size(max = 3)
    @JsonProperty("tipo_movimiento")
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;
    @JsonProperty("id_remitente")
    @Column(name = "id_remitente")
    private Integer idRemitente;
    @JsonProperty("id_destino")
    @Column(name = "id_destino")
    private Integer idDestino;
    @JsonProperty("descuento_aplicado")
    @Column(name = "descuento_aplicado")
    private Double descuentoAplicado;
    @Size(max = 3)
    @Column(name = "estado")
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario vendedor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario cliente;

    @JsonProperty("valor_movimiento")
    @Column(name = "valor_movimiento")
    private Double valorMovimiento;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_movimiento")
    @JsonProperty("item_movimientos")
    private List<ItemMovimiento> itemMovimientos;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_movimiento")
    private Date fechaMovimiento;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    public Movimiento(String descripcion, String tipoMovimiento, Integer idRemitente,
                      Integer idDestino, Double descuentoAplicado, Usuario vendedor,
                      Usuario cliente, Double valorMovimiento, List<ItemMovimiento> itemMovimientos,
                      Double valorPagado, String estado) {
        this.descripcion = descripcion;
        this.tipoMovimiento = tipoMovimiento;
        this.idRemitente = idRemitente;
        this.idDestino = idDestino;
        this.descuentoAplicado = descuentoAplicado;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.valorMovimiento = valorMovimiento;
        this.itemMovimientos = itemMovimientos;
        this.valorPagado = valorPagado;
        this.estado = estado;
    }

    public void addItemsMovimiento(ItemMovimiento item) {
        this.itemMovimientos.add(item);
    }

    @PrePersist
    public void prePersist() {
        this.fechaMovimiento = new Date();
        this.estado = ((valorMovimiento - valorPagado) <= 0D) ? ConstantesSistema.MOVIMIENTO_ESTADO_PAGADO : ConstantesSistema.MOVIMIENTO_ESTADO_ACTIVO;
        this.pendiente = (valorPagado - valorMovimiento) < 0 ? (valorPagado - valorMovimiento) * -1 : 0;
        this.cambio = (valorPagado - valorMovimiento) > 0 ? (valorPagado - valorMovimiento) : 0;

    }

    @PreUpdate
    public void preUpdate() {
        this.fechaModificacion = new Date();
        this.pendiente = (valorPagado - pendiente) < 0 ? (valorPagado - pendiente) * -1 : 0;
        this.cambio = (valorPagado - pendiente) > 0 ? (valorPagado - pendiente) : 0;

    }

    public Double getTotalVenta() {
        Double total = 0.0;
        for (ItemMovimiento item : itemMovimientos) {
            total += item.calcularImporteVenta();
        }
        return total;
    }

    public Double getTotalCompra() {
        Double total = 0.0;
        for (ItemMovimiento item : itemMovimientos) {
            total += item.calcularImporteCompra();
        }
        return total;
    }

    public Double calcularDescuento() {
        if (this.descuentoAplicado != 0) {
            return valorMovimiento * (descuentoAplicado / 100);
        }
        return 0D;
    }


}