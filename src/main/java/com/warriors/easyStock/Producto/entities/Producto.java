package com.warriors.easyStock.Producto.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.warriors.easyStock.Categoria.entities.Categoria;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto implements Serializable {
    @Id
    @Column(name = "id_producto", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @JsonProperty("serial_id")
    @Column(name = "serial_id", nullable = false, unique = true)
    private String serialID;

    @Size(max = 255)
    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Size(max = 8)
    @NotNull
    @JsonProperty("unidad_medida")
    @Column(name = "unidad_medida", nullable = false, length = 8)
    private String unidadMedida;

    @NotNull
    @JsonProperty("valor_compra")
    @Column(name = "valor_compra", nullable = false)
    private Double valorCompra;

    @NotNull
    @JsonProperty("valor_venta")
    @Column(name = "valor_venta", nullable = false)
    private Double valorVenta;

    @Column(name = "ganancia")
    private Double ganancia;

    @NotNull
    @Column(name = "estado", nullable = false)
    private Boolean estado = false;

    @NotNull
    @JsonProperty("cantidad_stock")
    @Column(name = "cantidad_stock", nullable = false)
    private int cantidadStock;
    @JsonProperty("fecha_creacion")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @JsonProperty("fecha_expiracion")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_expiracion")
    private Date fechaExpiracion;

    @JsonProperty("fecha_modificacion")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JsonProperty("categoria_id")
    @JoinColumn(name = "id_categoria")
    private Categoria Categoria;
    public Double calcularGanancia() {
        return (valorVenta - valorCompra);
    }

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = new Date();
        this.estado = cantidadStock > 0 ? true : false;
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaModificacion = new Date();
        this.estado = cantidadStock > 0 ? true : false;
    }

}