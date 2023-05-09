package com.warriors.easyStock.Usuario.entities;

import com.warriors.easyStock.roles.entities.Rol;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    @Id
    @Column(name = "id_usuario", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Size(max = 20)
    @NotNull
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Size(max = 100)
    @Column(name = "direccion", length = 100)
    private String direccion;

    @Size(max = 100)
    @Column(name = "ciudad", length = 100)
    private String ciudad;

    @Size(max = 100)
    @Column(name = "usuario", length = 100)
    private String usuario;

    @Size(max = 150)
    @Column(name = "contrasena", length = 150)
    private String contrasena;

    @Column(name = "estado")
    private Boolean estado;

    @Size(max = 200)
    @Column(name = "correo", length = 200)
    private String correo;

    @Size(max = 200)
    @Column(name = "token_password", length = 200)
    private String tokenPassword;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ultima_modificacion")
    Date fechaUltimaModificacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    Date fechaCreacion;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_usuario", "id_rol"})}

    )
    private Set<Rol> roles = new HashSet<>();


    public Usuario(String nombre, String telefono, String direccion,
                   String ciudad, String usuario, String contrasena,
                   Boolean estado, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estado = estado;
        this.correo = correo;
    }

    @PrePersist
    public void Prepersist(){
        this.fechaCreacion = new Date();
    }

    @PreUpdate
    public void Preupdate(){
        this.fechaUltimaModificacion = new Date();
    }
}