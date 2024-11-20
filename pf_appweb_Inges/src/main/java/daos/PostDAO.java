/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import dtos.PostDTO;
import interfaces.IPostDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Post;

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
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, post.getTitulo());
            statement.setString(2, post.getContenido());
            statement.setBoolean(3, post.isAnclado());
            statement.setLong(4, post.getUsuario().getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
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
    
}
