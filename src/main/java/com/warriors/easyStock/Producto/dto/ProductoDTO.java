package com.warriors.easyStock.Producto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private String descripcion;
    private String unidadMedida;
    private Double valorCompra;
    private Double valorVenta;
    private Double cantidadStock;
}
