package com.warriors.easyStock.movimiento.service;

import com.warriors.easyStock.movimiento.dto.MovimientoRespnseDTO;
import com.warriors.easyStock.movimiento.entities.Movimiento;

import java.util.List;

public interface IMovimientoService {
    public MovimientoRespnseDTO guardarMovimiento(Movimiento movimiento, int codigoVendedor, int codigoCliente);

    public List<MovimientoRespnseDTO> listarMovimientos();

    public MovimientoRespnseDTO anularMovimiento(int idMovimiento);
}
