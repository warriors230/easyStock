package com.warriors.easyStock.Movimiento.service;

import com.warriors.easyStock.Movimiento.entities.Movimiento;

public interface IMovimientoService {
    public Movimiento guardarMovimiento(Movimiento movimiento, int codigoVendedor, int codigoCliente);
}
