/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import interfaces.IComentarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Comentario;
import modelo.Usuario;

/**
 *
 * @author Fran
 */
public class ComentarioDAO implements IComentarioDAO {

    @Override
    public boolean agregarComentario(Comentario comentario) {
        String sql = "INSERT INTO comentarios (contenido, usuario_id, post_id, fechaHora) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, comentario.getContenido());
            statement.setLong(2, comentario.getUsuario().getId());
            statement.setLong(3, comentario.getPost().getId());
            statement.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarComentario(long comentarioId) {
        String sql = "DELETE FROM comentarios WHERE id = ?";
        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, comentarioId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Devuelve true si se eliminó
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<Comentario> obtenerComentariosPorPublicacion(long postId) {
        List<Comentario> comentarios = new ArrayList<>();
        String sql = "SELECT * FROM comentarios WHERE post_id = ?";

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, postId);
            ResultSet resultSet = statement.executeQuery();


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
//                ComentarioDTO comentarioDTO = mapeos.comentarioEntidadToDTO(comentario);
                comentarios.add(comentario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comentarios;
    }

}
