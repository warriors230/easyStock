package com.warriors.easyStock.config.Security;

import com.warriors.easyStock.Usuario.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthCredentials extends Usuario {
    private String user;
    private String password;
}
