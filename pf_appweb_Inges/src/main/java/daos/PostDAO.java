/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import dtos.ComentarioDTO;
import dtos.PostDTO;
import dtos.UsuarioDTO;
import interfaces.IPostDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapeos.Mapeos;
import modelo.Post;
import modelo.Usuario;

/**
 *
 * @author Fran
 */
public class PostDAO implements IPostDAO {

    @Override
    public Post consultarPost(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarPost'");
    }

    @Override
    public boolean agregarPost(PostDTO post) {
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
    public void modificarPost(PostDTO post) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarPost(PostDTO post) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PostDTO> obtenerTodasLasPublicaciones() {
        List<PostDTO> publicacionesDTO = new ArrayList<>();
        String sql = "SELECT * FROM posts";

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            Mapeos mapeos = new Mapeos();
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
                PostDTO postDTO = mapeos.entidadToDTO(post);

                // Obtener y asignar comentarios
                List<ComentarioDTO> comentarios = comentarioDAO.obtenerComentariosPorPublicacion(post.getId());
                postDTO.setComentarios(comentarios);

                publicacionesDTO.add(postDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return publicacionesDTO;
    }

    @Override
    public List<PostDTO> obtenerPublicacionesAncladas() {
        List<PostDTO> publicacionesAncladasDTO = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE anclado = TRUE";

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            Mapeos mapeos = new Mapeos();

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
                PostDTO postDTO = mapeos.entidadToDTO(post);
                publicacionesAncladasDTO.add(postDTO);
            }
        } catch (SQLException e) {
        }

        return publicacionesAncladasDTO;
    }

}
