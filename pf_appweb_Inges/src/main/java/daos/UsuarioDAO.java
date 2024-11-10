/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import dtos.UsuarioDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import interfaces.IUsuarioDAO;
import mapeos.Encriptar;
import mapeos.Mapeos;
import modelo.Genero;
import modelo.TipoUsuario;
import modelo.Usuario;

/**
 *
 * @author Fran
 */
public class UsuarioDAO implements IUsuarioDAO {

    Encriptar encriptar = new Encriptar();
    Mapeos mapeos = new Mapeos();

    @Override
    public void registrarUsuario(UsuarioDTO usuarioDTO) {
        try {
            // Convertir UsuarioDTO a Usuario utilizando Mapeos
            Usuario usuario = mapeos.dtoToUsuario(usuarioDTO);

            // Encriptar la contraseña antes de guardarla en la base de datos
            String contraseniaEncriptada = Encriptar.encriptar(usuario.getContrasenia());
            usuario.setContrasenia(contraseniaEncriptada);

            String sql = "INSERT INTO usuarios (nombreCompleto, correo, contrasenia, telefono, avatar, direccion, fechaNacimiento, genero, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection connection = ConexionBD.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, usuario.getNombreCompleto());
                statement.setString(2, usuario.getCorreo());
                statement.setString(3, usuario.getContrasenia());
                statement.setString(4, usuario.getTelefono());
                statement.setString(5, usuario.getAvatar());
                statement.setString(6, usuario.getDireccion()); // Guardar la dirección como un solo String
                statement.setDate(7, new Date(usuario.getFechaNacimiento().getTime()));
                statement.setString(8, usuario.getGenero().name());
                statement.setString(9, usuario.getTipoUsuario().name());

                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        String sql = "UPDATE Usuarios SET nombreCompleto = ?, correo = ?, contrasenia = ?, telefono = ?, avatar = ?, fechaNacimiento = ?, genero = ?, tipo = ? WHERE id = ?";

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, usuario.getNombreCompleto());
            statement.setString(2, usuario.getCorreo());
            statement.setString(3, usuario.getContrasenia());
            statement.setString(4, usuario.getTelefono());
            statement.setString(5, usuario.getAvatar());
            statement.setDate(6, new Date(usuario.getFechaNacimiento().getTime()));
            statement.setString(7, usuario.getGenero().name());
            statement.setString(8, usuario.getTipoUsuario().name());
            statement.setLong(9, usuario.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        String sql = "DELETE FROM Usuarios WHERE id = ?";

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, usuario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario obtenerUsuario(int id) {
        Mapeos mapeos = new Mapeos();
        String sql = "SELECT * FROM Usuarios WHERE id = ?";
        Usuario usuario = null;

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setId(resultSet.getLong("id"));
                usuario.setNombreCompleto(resultSet.getString("nombreCompleto"));
                usuario.setCorreo(resultSet.getString("correo"));
                usuario.setTelefono(resultSet.getString("telefono"));
                usuario.setAvatar(resultSet.getString("avatar"));
                usuario.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
                usuario.setGenero(mapeos.stringToGenero(resultSet.getString("genero")));
                usuario.setTipoUsuario(mapeos.stringToTipoUsuario(resultSet.getString("tipo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) throws Exception {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuarios WHERE correo = ?";

        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, correo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setId(resultSet.getLong("id"));
                usuario.setNombreCompleto(resultSet.getString("nombreCompleto"));
                usuario.setCorreo(resultSet.getString("correo"));
                
                // Desencriptar la contraseña antes de asignarla
                String contraseniaEncriptada = resultSet.getString("contrasenia");
                String contraseniaDesencriptada = encriptar.desencriptar(contraseniaEncriptada);
                usuario.setContrasenia(contraseniaDesencriptada);
                
                usuario.setTelefono(resultSet.getString("telefono"));
                usuario.setAvatar(resultSet.getString("avatar"));
                usuario.setDireccion(resultSet.getString("direccion"));
                usuario.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
                usuario.setGenero(resultSet.getString("genero").equalsIgnoreCase("masculino") ? Genero.MASCULINO : Genero.FEMENINO);
                usuario.setTipoUsuario(resultSet.getString("tipo").equalsIgnoreCase("Admor") ? TipoUsuario.ADMOR : TipoUsuario.NORMAL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }


    private void validarUsuario(UsuarioDTO usuario) {
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
            throw new IllegalArgumentException("La dirección es obligatoria.");
        }

    }

}
