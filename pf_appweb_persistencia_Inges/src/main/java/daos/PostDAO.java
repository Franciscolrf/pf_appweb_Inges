/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import interfaces.IPostDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Post;
import modelo.Usuario;

/**
 *
 * @author Fran
 */
public class PostDAO implements IPostDAO {

    @Override
    public boolean agregarPost(Post post) {
        String sql = "INSERT INTO posts (titulo, contenido, anclado, usuario_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, post.getTitulo());
            statement.setString(2, post.getContenido());
            statement.setBoolean(3, post.isAnclado());
            statement.setLong(4, post.getUsuario().getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        return false;

    }

    @Override
    public boolean modificarPost(Post post) {
        String sql = "UPDATE posts SET titulo = ?, contenido = ? WHERE id = ?";

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, post.getTitulo());
            statement.setString(2, post.getContenido());
            statement.setLong(3, post.getId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarPost(long postId) {
        String sql = "DELETE FROM posts WHERE id = ?";
        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, postId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Devuelve true si se eliminó
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Post> obtenerTodasLasPublicaciones() {
        List<Post> publicaciones = new ArrayList<>();
        String sql = "SELECT * FROM posts";

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            ComentarioDAO comentarioDAO = new ComentarioDAO(); // Instancia para obtener comentarios

            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setTitulo(resultSet.getString("titulo"));
                post.setContenido(resultSet.getString("contenido"));
                post.setAnclado(resultSet.getBoolean("anclado"));
                post.setFechaHoraCreacion(resultSet.getTimestamp("fechaHoraCreacion"));
                post.setFechaHoraEdicion(resultSet.getTimestamp("fechaHoraEdicion"));

                // Obtener el usuario
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.obtenerUsuario(resultSet.getInt("usuario_id"));
                post.setUsuario(usuario);

                // Convertir a DTO
//                PostDTO postDTO = mapeos.entidadToDTO(post);

                // Obtener y asignar comentarios
//                List<Comentario> comentarios = comentarioDAO.obtenerComentariosPorPublicacion(post.getId());
//                postDTO.setComentarios(comentarios);

                publicaciones.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return publicaciones;
    }

    @Override
    public List<Post> obtenerPublicacionesAncladas() {
        List<Post> publicacionesAncladas = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE anclado = TRUE";

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {


            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setTitulo(resultSet.getString("titulo"));
                post.setContenido(resultSet.getString("contenido"));
                post.setAnclado(resultSet.getBoolean("anclado"));
                post.setFechaHoraCreacion(resultSet.getTimestamp("fechaHoraCreacion"));
                post.setFechaHoraEdicion(resultSet.getTimestamp("fechaHoraEdicion"));

                // Obtener el usuario
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.obtenerUsuario(resultSet.getInt("usuario_id"));
                post.setUsuario(usuario);

                // Convertir a DTO
//                PostDTO postDTO = mapeos.entidadToDTO(post);
                publicacionesAncladas.add(post);
            }
        } catch (SQLException e) {
        }

        return publicacionesAncladas;
    }

    @Override
    public Post obtenerPostPorId(long postId) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        Post post = new Post();

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, postId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Mapear los datos del post
                
                post.setId(resultSet.getLong("id"));
                post.setTitulo(resultSet.getString("titulo"));
                post.setContenido(resultSet.getString("contenido"));
                post.setAnclado(resultSet.getBoolean("anclado"));
                post.setFechaHoraCreacion(resultSet.getTimestamp("fechaHoraCreacion"));
                post.setFechaHoraEdicion(resultSet.getTimestamp("fechaHoraEdicion"));

                // Obtener el usuario (autor del post)
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.obtenerUsuario(resultSet.getInt("usuario_id"));
                post.setUsuario(usuario);

                // Mapear los comentarios relacionados con el post
//                ComentarioDAO comentarioDAO = new ComentarioDAO();
//                List<Comentario> comentarios = comentarioDAO.obtenerComentariosPorPublicacion(post.getId());
//
//                // Convertir el post a DTO
////                postDTO = mapeos.entidadToDTO(post);
//                postDTO.setComentarios(comentarios); // Asociar los comentarios
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }

}
