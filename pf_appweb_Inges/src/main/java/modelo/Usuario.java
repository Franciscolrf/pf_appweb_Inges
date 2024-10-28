/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Fran
 */
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombreCompleto;
    private String correo;
    private String contrasenia;
    private String telefono;
    private String avatar;
    private Date fechaNacimiento;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @OneToOne(mappedBy = "usuario")
    private Direccion direccion;

    public Usuario() {
    }

    public Usuario(String nombreCompleto, String correo, String contrasenia, String telefono, String avatar, Date fechaNacimiento, Genero genero, TipoUsuario tipoUsuario, Direccion direccion) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.avatar = avatar;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.tipoUsuario = tipoUsuario;
        this.direccion = direccion;
    }

    public Usuario(Long id, String nombreCompleto, String correo, String contrasenia, String telefono, String avatar, Date fechaNacimiento, Genero genero, TipoUsuario tipoUsuario, Direccion direccion) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.avatar = avatar;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.tipoUsuario = tipoUsuario;
        this.direccion = direccion;
    }

    public Usuario(String nombreCompleto, String correo, String contrasenia, String telefono, String avatar, Date fechaNacimiento, Genero genero, TipoUsuario tipoUsuario) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.avatar = avatar;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombreCompleto=" + nombreCompleto + ", correo=" + correo + ", contrasenia="
                + contrasenia + ", telefono=" + telefono + ", avatar=" + avatar + ", fechaNacimiento=" + fechaNacimiento
                + ", genero=" + genero + ", tipoUsuario=" + tipoUsuario + ", direccion=" + direccion + "]";
    }

    
    
}
