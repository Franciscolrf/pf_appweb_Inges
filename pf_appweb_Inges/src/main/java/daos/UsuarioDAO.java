/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import interfaces.IUsuarioDAO;
import modelo.Usuario;

/**
 *
 * @author Fran
 */
public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public void registrarUsuario(Usuario usuario) {
        validarUsuario(usuario);
        String sql = "INSERT INTO Usuarios (nombreCompleto, correo, contrasenia, telefono, avatar, genero, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConexionBD.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, usuario.getNombreCompleto());
            statement.setString(2, usuario.getCorreo());
            statement.setString(3, usuario.getContrasenia());
            statement.setString(4, usuario.getTelefono());
            statement.setString(5, usuario.getAvatar());
            statement.setString(6, usuario.getGenero().name());
            statement.setString(7, usuario.getTipoUsuario().name());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarUsuario'");
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarUsuario'");
    }

    @Override
    public void obtenerUsuario(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerUsuario'");
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario.getNombreCompleto() == null || usuario.getNombreCompleto().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo es obligatorio.");
        }
        if (usuario.getCorreo() == null || !usuario.getCorreo().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("El correo es inválido.");
        }
        if (usuario.getContrasenia() == null || usuario.getContrasenia().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }
        if (usuario.getTelefono() == null || !usuario.getTelefono().matches("\\d{10}")) {
            throw new IllegalArgumentException("El teléfono debe tener 10 dígitos.");
        }
        if (usuario.getDireccion() != null) {
            if (usuario.getDireccion().getCalle() == null || usuario.getDireccion().getCalle().isEmpty()) {
                throw new IllegalArgumentException("La calle es obligatoria.");
            }
            if (usuario.getDireccion().getNumero() == null || usuario.getDireccion().getNumero().isEmpty()) {
                throw new IllegalArgumentException("El número es obligatorio.");
            }
            if (usuario.getDireccion().getCiudad() == null || usuario.getDireccion().getCiudad().isEmpty()) {
                throw new IllegalArgumentException("La ciudad es obligatoria.");
            }
        }

    }

}
