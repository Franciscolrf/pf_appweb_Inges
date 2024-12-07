package fachada;

import dtos.ComentarioDTO;
import dtos.PostDTO;
import dtos.UsuarioDTO;
import negocio.ComentarioBO;
import negocio.PostBO;
import negocio.UsuarioBO;

import java.util.List;

public class FachadaApp implements IFachadaApp {

    private final UsuarioBO usuarioBO;
    private final PostBO postBO;
    private final ComentarioBO comentarioBO;

    public FachadaApp() {
        this.usuarioBO = new UsuarioBO();
        this.postBO = new PostBO();
        this.comentarioBO = new ComentarioBO();
    }

    // Métodos relacionados con usuarios
    @Override
    public boolean registrarUsuario(UsuarioDTO usuario) {
        return usuarioBO.registrarUsuario(usuario);
    }

    @Override
    public boolean modificarUsuario(UsuarioDTO usuario, String nuevaContrasenia) {
        return usuarioBO.modificarUsuario(usuario, nuevaContrasenia);
    }

    @Override
    public boolean eliminarUsuario(long idUsuario) {
        return usuarioBO.eliminarUsuario(idUsuario);
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(int id) {
        return usuarioBO.obtenerUsuarioPorId(id);
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorCorreo(String correo) {
        return usuarioBO.obtenerUsuarioPorCorreo(correo);
    }

    @Override
    public UsuarioDTO validarLogin(String correo, String contrasenia) {
        return usuarioBO.validarLogin(correo, contrasenia);
    }

    // Métodos relacionados con publicaciones
    @Override
    public boolean agregarPost(PostDTO post) {
        return postBO.agregarPost(post);
    }

    @Override
    public boolean modificarPost(PostDTO post) {
        return postBO.modificarPost(post);
    }

    @Override
    public boolean eliminarPost(long postId) {
        return postBO.eliminarPost(postId);
    }

    @Override
    public List<PostDTO> obtenerTodasLasPublicaciones() {
        return postBO.obtenerTodasLasPublicaciones();
    }

    @Override
    public List<PostDTO> obtenerPublicacionesAncladas() {
        return postBO.obtenerPublicacionesAncladas();
    }

    @Override
    public PostDTO obtenerPostPorId(long postId) {
        return postBO.obtenerPostPorId(postId);
    }

    // Métodos relacionados con comentarios
    @Override
    public boolean agregarComentario(ComentarioDTO comentario) {
        return comentarioBO.agregarComentario(comentario);
    }

    @Override
    public boolean eliminarComentario(long comentarioId) {
        return comentarioBO.eliminarComentario(comentarioId);
    }

    @Override
    public List<ComentarioDTO> obtenerComentariosPorPublicacion(long postId) {
        return comentarioBO.obtenerComentariosPorPublicacion(postId);
    }
}
