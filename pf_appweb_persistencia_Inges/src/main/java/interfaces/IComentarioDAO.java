/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import modelo.Comentario;

/**
 *
 * @author Fran
 */
public interface IComentarioDAO {
    
    boolean agregarComentario(Comentario comentario);
    boolean eliminarComentario(long comentarioId);
    public List<Comentario> obtenerComentariosPorPublicacion(long postId);
}
