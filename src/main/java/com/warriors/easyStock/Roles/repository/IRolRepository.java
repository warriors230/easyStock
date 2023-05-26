package com.warriors.easyStock.Roles.repository;

import com.warriors.easyStock.Roles.entities.Rol;
import com.warriors.easyStock.Roles.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IRolRepository extends JpaRepository<Rol,Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
