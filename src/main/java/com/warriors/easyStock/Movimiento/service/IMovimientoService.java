package com.warriors.easyStock.Movimiento.service;

import com.warriors.easyStock.Movimiento.dto.MovimientoRespnseDTO;
import com.warriors.easyStock.Movimiento.entities.Movimiento;

public interface IMovimientoService {
    public MovimientoRespnseDTO guardarMovimiento(Movimiento movimiento, int codigoVendedor, int codigoCliente);
}
