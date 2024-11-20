/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import dtos.ComentarioDTO;
import interfaces.IComentarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapeos.Mapeos;
import modelo.Comentario;
import modelo.Usuario;

/**
 *
 * @author Fran
 */
public class ComentarioDAO implements IComentarioDAO{

    @Override
    public void agregarComentario(Comentario comentario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarComentario'");
    }

    @Override
    public void eliminarComentario(Comentario comentario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarComentario'");
    }

    @Override
    public void modificarComentario(Comentario comentario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarComentario'");
    }
    
    public List<ComentarioDTO> obtenerComentariosPorPublicacion(long postId) {
    List<ComentarioDTO> comentariosDTO = new ArrayList<>();
    String sql = "SELECT * FROM comentarios WHERE post_id = ?";

    try (Connection connection = ConexionBD.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setLong(1, postId);
        ResultSet resultSet = statement.executeQuery();

        Mapeos mapeos = new Mapeos();

        while (resultSet.next()) {
            Comentario comentario = new Comentario();
            comentario.setId(resultSet.getLong("id"));
            comentario.setContenido(resultSet.getString("contenido"));
            comentario.setFechaHora(resultSet.getTimestamp("fechaHora"));

            // Obtener el usuario
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.obtenerUsuario(resultSet.getInt("usuario_id"));
            comentario.setUsuario(usuario);

            // Convertir a DTO
            ComentarioDTO comentarioDTO = mapeos.comentarioEntidadToDTO(comentario);
            comentariosDTO.add(comentarioDTO);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return comentariosDTO;
}

    
}
