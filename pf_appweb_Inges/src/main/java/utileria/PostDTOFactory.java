/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utileria;

import dtos.PostDTO;
import dtos.UsuarioDTO;

/**
 *
 * @author franc
 */
public class PostDTOFactory {
    public static PostDTO createPost(String titulo, String contenido, UsuarioDTO usuario, boolean anclado){
    
    PostDTO nuevoPost = new PostDTO();
    nuevoPost.setTitulo(titulo);
    nuevoPost.setContenido(contenido);
    nuevoPost.setUsuario(usuario);
    nuevoPost.setAnclado(anclado);
    return nuevoPost;

    }
}
