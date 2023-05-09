package com.warriors.easyStock.roles.service;

import com.warriors.easyStock.roles.entities.Rol;
import com.warriors.easyStock.roles.enums.RolNombre;

import java.util.Optional;

public interface IRolService {
    public Optional<Rol> getByRolNombre(RolNombre rolNombre);
    public void save(Rol rol);
}
