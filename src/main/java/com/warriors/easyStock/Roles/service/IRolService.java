package com.warriors.easyStock.Roles.service;

import com.warriors.easyStock.Roles.entities.Rol;
import com.warriors.easyStock.Roles.enums.RolNombre;

import java.util.Optional;

public interface IRolService {
    public Optional<Rol> getByRolNombre(RolNombre rolNombre);
    public void save(Rol rol);
}
