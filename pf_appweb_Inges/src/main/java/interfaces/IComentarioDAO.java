/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ComentarioDTO;
import java.util.List;
import modelo.Comentario;

/**
 *
 * @author Fran
 */
public interface IComentarioDAO {
    
    boolean agregarComentario(ComentarioDTO comentario);
    void eliminarComentario(Comentario comentario);
    void modificarComentario(Comentario comentario);
    public List<ComentarioDTO> obtenerComentariosPorPublicacion(long postId);
}
