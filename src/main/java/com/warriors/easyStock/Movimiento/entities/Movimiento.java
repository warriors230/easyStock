package com.warriors.easyStock.Movimiento.entities;

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
    @Column(name = "tipo_mov")
    private String tipoMov;

    @Column(name = "id_remitente")
    private Integer idRemitente;

    @Column(name = "id_destino")
    private Integer idDestino;

    @Column(name = "descuento_aplicado")
    private Double descuentoAplicado;


    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario idVendedor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario idCliente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_movimiento")
    private List<ItemMovimiento> itemMovimientos;

    public void addItemsMovimiento(ItemMovimiento item) {
        this.itemMovimientos.add(item);
    }

    @PrePersist
    public void Prepersist() {
        this.fechaMovimiento = new Date();

    }

    public Double getTotal(){
        Double total = 0.0;
        for (ItemMovimiento item:itemMovimientos) {
            total += item.calcularImporte();
        }
        return total;
    }

}