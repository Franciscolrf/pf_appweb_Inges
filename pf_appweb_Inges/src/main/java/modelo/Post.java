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
import java.util.Date;

/**
 *
 * @author Fran
 */
@Entity
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;
    private String contenido;
    private Date fechaHoraCreacion;
    private Date fechaHoraEdicion;

    @ManyToOne
    private Usuario usuario;

    public Post() {
    }

    public Post(String titulo, String contenido, Date fechaHoraCreacion, Date fechaHoraEdicion, Usuario usuario) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.fechaHoraEdicion = fechaHoraEdicion;
        this.usuario = usuario;
    }

    public Post(Long id, String titulo, String contenido, Date fechaHoraCreacion, Date fechaHoraEdicion, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.fechaHoraEdicion = fechaHoraEdicion;
        this.usuario = usuario;
    }

    public Post(String titulo, String contenido, Date fechaHoraCreacion, Date fechaHoraEdicion) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.fechaHoraEdicion = fechaHoraEdicion;
    }

    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Date fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public Date getFechaHoraEdicion() {
        return fechaHoraEdicion;
    }

    public void setFechaHoraEdicion(Date fechaHoraEdicion) {
        this.fechaHoraEdicion = fechaHoraEdicion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", titulo=" + titulo + ", contenido=" + contenido + ", fechaHoraCreacion="
                + fechaHoraCreacion + ", fechaHoraEdicion=" + fechaHoraEdicion + ", usuario=" + usuario + "]";
    }



    
    
}
