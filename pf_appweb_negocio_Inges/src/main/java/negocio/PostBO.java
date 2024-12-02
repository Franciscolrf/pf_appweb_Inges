package negocio;

import conversor.Mapeos;
import daos.ComentarioDAO;
import daos.PostDAO;
import dtos.ComentarioDTO;
import dtos.PostDTO;
import interfaces.IPostBO;
import modelo.Post;

import java.util.ArrayList;
import java.util.List;
import modelo.Comentario;

public class PostBO implements IPostBO {

    private PostDAO postDAO;
    private Mapeos mapeos;
    private ComentarioDAO comentariosDAO;

    public PostBO() {
        this.postDAO = new PostDAO(); // Instanciamos el DAO
        this.mapeos = new Mapeos();
        this.comentariosDAO = new ComentarioDAO();
    }

    @Override
    public boolean agregarPost(PostDTO post) {
        return postDAO.agregarPost(mapeos.dtoToEntidad(post)); // Delegamos la operación al DAO
    }

    @Override
    public boolean modificarPost(PostDTO post) {
        return postDAO.modificarPost(mapeos.dtoToEntidad(post)); // Delegamos la operación al DAO
    }

    @Override
    public boolean eliminarPost(long postId) {
        return postDAO.eliminarPost(postId); // Delegamos la operación al DAO
    }

    @Override
    public List<PostDTO> obtenerTodasLasPublicaciones() {
        List<Post> publicaciones = postDAO.obtenerTodasLasPublicaciones();
        List<PostDTO> publicacionesDTO = new ArrayList<>(); // Delegamos la operación al DAO

        for (Post post : publicaciones) {
            List<ComentarioDTO> comentariosDTO = new ArrayList<>();
            for (Comentario comentario : comentariosDAO.obtenerComentariosPorPublicacion(post.getId())) {
                comentariosDTO.add(mapeos.comentarioEntidadToDTO(comentario));
            }
            PostDTO postDTO = mapeos.entidadToDTO(post);
            postDTO.setComentarios(comentariosDTO);
            publicacionesDTO.add(postDTO);
        }
        return publicacionesDTO; // Delegamos la operación al DAO
    }

    @Override
    public List<PostDTO> obtenerPublicacionesAncladas() {
        List<Post> publicaciones = postDAO.obtenerPublicacionesAncladas();
        List<PostDTO> publicacionesDTO = new ArrayList<>(); // Delegamos la operación al DAO

        for (Post post : publicaciones) {
            List<ComentarioDTO> comentariosDTO = new ArrayList<>();
            for (Comentario comentario : comentariosDAO.obtenerComentariosPorPublicacion(post.getId())) {
                comentariosDTO.add(mapeos.comentarioEntidadToDTO(comentario));
            }
            PostDTO postDTO = mapeos.entidadToDTO(post);
            postDTO.setComentarios(comentariosDTO);
            publicacionesDTO.add(postDTO);
        }
        return publicacionesDTO; // Delegamos la operación al DAO
    }

    @Override
    public PostDTO obtenerPostPorId(long postId) {
        return mapeos.entidadToDTO(postDAO.obtenerPostPorId(postId)); // Delegamos la operación al DAO
    }
}
