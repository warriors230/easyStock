package com.warriors.easyStock.Utils;

import com.warriors.easyStock.roles.entities.Rol;
import com.warriors.easyStock.roles.enums.RolNombre;
import com.warriors.easyStock.roles.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;




@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    IRolService rolService;


    @Override
    public void run(String... args) throws Exception {
       /* Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolsupremo = new Rol(RolNombre.ROLE_SUPREMO);
        Rol rolUser = new Rol(RolNombre.ROLE_USUARIO);
        Rol rolVendedor = new Rol(RolNombre.ROLE_VENDEDOR);
        Rol rolCliente = new Rol(RolNombre.ROLE_CLIENTE);
        rolService.save(rolsupremo);
        rolService.save(rolAdmin);
        rolService.save(rolUser);
        rolService.save(rolVendedor);
        rolService.save(rolCliente);*/

    }
}
