package com.warriors.easyStock.Movimiento.repository;

import com.warriors.easyStock.Movimiento.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovimientoRepository extends JpaRepository<Movimiento,Integer> {
}
