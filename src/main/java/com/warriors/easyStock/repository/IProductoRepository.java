package com.warriors.easyStock.repository;

import com.warriors.easyStock.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto,Integer> {
}
