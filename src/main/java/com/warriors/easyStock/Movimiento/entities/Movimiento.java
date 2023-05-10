package com.warriors.easyStock.Movimiento.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.warriors.easyStock.Usuario.entities.Usuario;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_movimiento")
    private Date fechaMovimiento;

    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;

    @Size(max = 3)
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;

    @Column(name = "id_remitente")
    private Integer idRemitente;

    @Column(name = "id_destino")
    private Integer idDestino;

    @Column(name = "descuento_aplicado")
    private Double descuentoAplicado;


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

    public void addItemsMovimiento(ItemMovimiento item) {
        this.itemMovimientos.add(item);
    }

    @PrePersist
    public void Prepersist() {
        this.fechaMovimiento = new Date();

    }

    public Double getTotal() {
        Double total = 0.0;
        for (ItemMovimiento item : itemMovimientos) {
            total += item.calcularImporte();
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