/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

/**
 *
 * @author Fran
 */
@Entity
public class Direccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String calle;
    private String numero;
    private String ciudad;

    @ManyToOne
    private Usuario usuario;

    public Direccion() {
    }

    public Direccion(String calle, String numero, String ciudad, Usuario usuario) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.usuario = usuario;
    }

    public Direccion(Long id, String calle, String numero, String ciudad, Usuario usuario) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.usuario = usuario;
    }

    public Direccion(String calle, String numero, String ciudad) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
    
}
