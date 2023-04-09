package com.warriors.easyStock.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "movimiento")
public class Movimiento {
    @Id
    @Column(name = "id_movimiento", nullable = false)
    private Integer id;

    @Column(name = "fecha_movimiento")
    private LocalDate fechaMovimiento;

    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;

    @Size(max = 255)
    @Column(name = "tipo_mov")
    private String tipoMov;

    @Column(name = "id_remitente")
    private Integer idRemitente;

    @Column(name = "id_destino")
    private Integer idDestino;

    @Column(name = "descuento_aplicado")
    private Double descuentoAplicado;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente idCliente;

    @Column(name = "id_producto")
    private Integer idProducto;

}