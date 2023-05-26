package com.warriors.easyStock.movimiento.repository;

import com.warriors.easyStock.movimiento.entities.ItemMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemMovimientoRepository extends JpaRepository<ItemMovimiento, Integer> {
}
