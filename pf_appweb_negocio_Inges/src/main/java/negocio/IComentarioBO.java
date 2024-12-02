package interfaces;

import dtos.ComentarioDTO;
import modelo.Comentario;

import java.util.List;

public interface IComentarioBO {

    // Método para agregar un nuevo comentario
    boolean agregarComentario(ComentarioDTO comentario);

    // Método para eliminar un comentario
    boolean eliminarComentario(long comentarioId);

    // Método para obtener los comentarios de una publicación
    List<ComentarioDTO> obtenerComentariosPorPublicacion(long postId);
}
