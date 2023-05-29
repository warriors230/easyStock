package com.warriors.easyStock.Producto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequestDTO {
    @JsonProperty("serial_id")
    private String serialID;
    private String descripcion;
    @JsonProperty("unidad_medida")
    private String unidadMedida;
    @JsonProperty("valor_compra")
    private Double valorCompra;
    @JsonProperty("valor_venta")
    private Double valorVenta;
    @JsonProperty("cantidad_stock")
    private int cantidadStock;
    private Boolean estado;
    @JsonProperty("fecha_expiracion")
    private Date fechaExpiracion;
    @JsonProperty("id_categoria")
    private int idCategoria;

}
