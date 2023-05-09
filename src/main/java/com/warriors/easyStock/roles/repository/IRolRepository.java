package com.warriors.easyStock.roles.repository;

import com.warriors.easyStock.roles.entities.Rol;
import com.warriors.easyStock.roles.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IRolRepository extends JpaRepository<Rol,Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
