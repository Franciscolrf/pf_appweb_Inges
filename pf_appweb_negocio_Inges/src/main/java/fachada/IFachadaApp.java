package fachada;

import dtos.ComentarioDTO;
import dtos.PostDTO;
import dtos.UsuarioDTO;

import java.util.List;

public interface IFachadaApp {
    // Métodos relacionados con usuarios
    boolean registrarUsuario(UsuarioDTO usuario);
    boolean modificarUsuario(UsuarioDTO usuario, String nuevaContrasenia);
    boolean eliminarUsuario(long idUsuario);
    UsuarioDTO obtenerUsuarioPorId(int id);
    UsuarioDTO obtenerUsuarioPorCorreo(String correo);
    UsuarioDTO validarLogin(String correo, String contrasenia);

    // Métodos relacionados con publicaciones
    boolean agregarPost(PostDTO post);
    boolean modificarPost(PostDTO post);
    boolean eliminarPost(long postId);
    List<PostDTO> obtenerTodasLasPublicaciones();
    List<PostDTO> obtenerPublicacionesAncladas();
    PostDTO obtenerPostPorId(long postId);

    // Métodos relacionados con comentarios
    boolean agregarComentario(ComentarioDTO comentario);
    boolean eliminarComentario(long comentarioId);
    List<ComentarioDTO> obtenerComentariosPorPublicacion(long postId);
}
