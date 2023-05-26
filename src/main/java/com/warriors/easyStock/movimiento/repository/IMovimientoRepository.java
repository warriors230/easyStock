package com.warriors.easyStock.movimiento.repository;

import com.warriors.easyStock.movimiento.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovimientoRepository extends JpaRepository<Movimiento,Integer> {
}
