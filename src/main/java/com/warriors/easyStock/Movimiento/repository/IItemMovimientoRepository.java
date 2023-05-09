package com.warriors.easyStock.Movimiento.repository;

import com.warriors.easyStock.Movimiento.entities.ItemMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemMovimientoRepository extends JpaRepository<ItemMovimiento, Integer> {
}
