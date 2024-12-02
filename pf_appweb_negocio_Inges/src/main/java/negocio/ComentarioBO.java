package bo;

import conversor.Mapeos;
import daos.ComentarioDAO;
import dtos.ComentarioDTO;
import interfaces.IComentarioBO;
import modelo.Comentario;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ComentarioBO implements IComentarioBO {

    private ComentarioDAO comentarioDAO;
    private Mapeos mapeos;

    public ComentarioBO() {
        this.comentarioDAO = new ComentarioDAO(); // Instanciamos el DAO
        this.mapeos = new Mapeos();
    }

    @Override
    public boolean agregarComentario(ComentarioDTO comentario) {
        return comentarioDAO.agregarComentario(mapeos.comentarioDtoToEntidad(comentario)); // Delegamos la operación al
                                                                                           // DAO
    }

    @Override
    public boolean eliminarComentario(long comentarioId) {
        return comentarioDAO.eliminarComentario(comentarioId); // Delegamos la operación al DAO
    }

    @Override
    public List<ComentarioDTO> obtenerComentariosPorPublicacion(long postId) {
        List<Comentario> comentarios = comentarioDAO.obtenerComentariosPorPublicacion(postId);
        List<ComentarioDTO> comentariosDTO = new ArrayList<>();
        for (Comentario comentario : comentarios) {
            comentariosDTO.add(mapeos.comentarioEntidadToDTO(comentario));
        }
        return comentariosDTO;
    }
}
