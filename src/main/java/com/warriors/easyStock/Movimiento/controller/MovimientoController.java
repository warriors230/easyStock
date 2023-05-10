package com.warriors.easyStock.Movimiento.controller;

import com.warriors.easyStock.Movimiento.entities.Movimiento;
import com.warriors.easyStock.Movimiento.service.IMovimientoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/movimiento")
public class MovimientoController {
    @Autowired
    IMovimientoService movimientoService;

    @PostMapping("/crear/{codCliente}/{codVendedor}")
    public ResponseEntity<?> crearMovimiento(@RequestBody Movimiento movimiento,
                                             @PathVariable int codCliente,
                                             @PathVariable int codVendedor) {
        return ResponseEntity.ok().body(movimientoService.guardarMovimiento(movimiento, codCliente, codVendedor));

    }
}
