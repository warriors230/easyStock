package com.warriors.easyStock.Movimiento.repository;

import com.warriors.easyStock.Movimiento.entities.MovimientoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovimientoProductoRepository extends JpaRepository<MovimientoProducto, Integer> {
}
