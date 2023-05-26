package com.warriors.easyStock.Roles.service.impl;

import com.warriors.easyStock.Roles.entities.Rol;
import com.warriors.easyStock.Roles.enums.RolNombre;
import com.warriors.easyStock.Roles.repository.IRolRepository;
import com.warriors.easyStock.Roles.service.IRolService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RolServiceImpl implements IRolService {
    @Autowired
    IRolRepository rolRepository;

    @Override
    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByRolNombre(rolNombre);

    }

    @Override
    public void save(Rol rol) {
        rolRepository.save(rol);
    }
}
