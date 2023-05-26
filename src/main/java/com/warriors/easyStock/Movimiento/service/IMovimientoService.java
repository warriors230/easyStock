package com.warriors.easyStock.Movimiento.service;

import com.warriors.easyStock.Movimiento.dto.MovimientoRespnseDTO;
import com.warriors.easyStock.Movimiento.entities.Movimiento;


import java.util.List;

public interface IMovimientoService {
    public MovimientoRespnseDTO guardarMovimiento(Movimiento movimiento, int codigoVendedor, int codigoCliente);

    public List<MovimientoRespnseDTO> listarMovimientos();

    public MovimientoRespnseDTO anularMovimiento(int idMovimiento);
}
