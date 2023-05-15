package com.warriors.easyStock.Movimiento.controller;

import com.warriors.easyStock.Movimiento.dto.MovimientoRespnseDTO;
import com.warriors.easyStock.Movimiento.entities.Movimiento;
import com.warriors.easyStock.Movimiento.service.IMovimientoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movimiento")
public class MovimientoController {
    @Autowired
    IMovimientoService movimientoService;
    @PreAuthorize("hasAnyRole('ROLE_SUPREMO','ROLE_ADMIN','ROLE_VENDEDOR')")
    @PostMapping("/crear/{codVendedor}/{codCliente}")
    public ResponseEntity<MovimientoRespnseDTO> crearMovimiento(@RequestBody Movimiento movimiento,
                                                                @PathVariable int codVendedor,
                                                                @PathVariable int codCliente)  {
        return ResponseEntity.ok().body(movimientoService.guardarMovimiento(movimiento, codVendedor, codCliente));

    }
    @PreAuthorize("hasAnyRole('ROLE_SUPREMO','ROLE_ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<MovimientoRespnseDTO>>listarMovimientos(){
        return ResponseEntity.ok().body(movimientoService.listarMovimientos());
    }
}
