/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import modelo.Post;

/**
 *
 * @author Fran
 */
public interface IPostDAO {
    
    boolean agregarPost(Post post);
    boolean modificarPost(Post post);
    boolean eliminarPost(long postId);
    public Post obtenerPostPorId(long postId);
    public List<Post> obtenerTodasLasPublicaciones();
    public List<Post> obtenerPublicacionesAncladas();
}
