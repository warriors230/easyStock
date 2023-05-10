package com.warriors.easyStock.Producto.repository;

import com.warriors.easyStock.Producto.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Integer> {
    Optional<Producto> findById(int id);
    Optional<Producto>findBySerialID(String serial);
    boolean existsByDescripcion(String descripcion);

}
