/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.PostDTO;
import java.util.List;

/**
 *
 * @author Fran
 */
public interface IPostDAO {
    
    boolean agregarPost(PostDTO post);
    boolean modificarPost(PostDTO post);
    boolean eliminarPost(long postId);
    public PostDTO obtenerPostPorId(long postId);
    public List<PostDTO> obtenerTodasLasPublicaciones();
    public List<PostDTO> obtenerPublicacionesAncladas();
}
