package com.warriors.easyStock.producto.repository;

import com.warriors.easyStock.producto.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Integer> {
    Optional<Producto> findById(int id);
    Optional<Producto>findBySerialID(String serial);
    boolean existsByDescripcion(String descripcion);

}
