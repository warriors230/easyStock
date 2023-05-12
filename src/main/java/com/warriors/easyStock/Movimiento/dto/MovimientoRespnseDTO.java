package com.warriors.easyStock.Movimiento.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.warriors.easyStock.Movimiento.entities.ItemMovimiento;
import com.warriors.easyStock.Usuario.dto.UsuarioResponseDTO;
import com.warriors.easyStock.Usuario.entities.Usuario;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class MovimientoRespnseDTO {
    @JsonProperty("id_movimiento")
    Integer id;
    @JsonProperty("fecha_movimiento")
    Date fechaMovimiento;
    String descripcion;
    @JsonProperty("tipo_movimiento")
    String tipoMovimiento;
    @JsonProperty("id_remitente")
    Integer idRemitente;
    @JsonProperty("id_destino")
    Integer idDestino;
    @JsonProperty("descuento_aplicado")
    Double descuentoAplicado;
    UsuarioResponseDTO vendedor;
    UsuarioResponseDTO cliente;
    @JsonProperty("valor_movimiento")
    Double valorMovimiento;
    @JsonProperty("item_movimientos")
    List<ItemMovimiento> itemMovimientos;
}
