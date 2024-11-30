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
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String contenido;
    private Date fechaHora;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Usuario usuario;

    
    public Comentario() {
    }

    public Comentario(String contenido, Date fechaHora, Post post, Usuario usuario) {
        this.contenido = contenido;
        this.fechaHora = fechaHora;
        this.post = post;
        this.usuario = usuario;
    }

    public Comentario(Long id, String contenido, Date fechaHora, Post post, Usuario usuario) {
        this.id = id;
        this.contenido = contenido;
        this.fechaHora = fechaHora;
        this.post = post;
        this.usuario = usuario;
    }

    public Comentario(String contenido, Date fechaHora) {
        this.contenido = contenido;
        this.fechaHora = fechaHora;
    }

    
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
        return "Comentario [id=" + id + ", contenido=" + contenido + ", fechaHora=" + fechaHora + ", post=" + post
                + ", usuario=" + usuario + "]";
    }

  
    
}
