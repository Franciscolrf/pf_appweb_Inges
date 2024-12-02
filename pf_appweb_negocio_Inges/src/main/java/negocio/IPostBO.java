package interfaces;

import dtos.PostDTO;
import modelo.Post;

import java.util.List;

public interface IPostBO {

    // Método para agregar una nueva publicación
    boolean agregarPost(PostDTO post);

    // Método para modificar una publicación
    boolean modificarPost(PostDTO post);

    // Método para eliminar una publicación
    boolean eliminarPost(long postId);

    // Método para obtener todas las publicaciones
    List<PostDTO> obtenerTodasLasPublicaciones();

    // Método para obtener todas las publicaciones ancladas
    List<PostDTO> obtenerPublicacionesAncladas();

    // Método para obtener una publicación por su ID
    PostDTO obtenerPostPorId(long postId);
}
