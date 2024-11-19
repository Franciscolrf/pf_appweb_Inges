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
import java.sql.SQLIntegrityConstraintViolationException;
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
    public boolean registrarUsuario(UsuarioDTO usuario) throws SQLIntegrityConstraintViolationException {
        String sql = "INSERT INTO usuarios (nombreCompleto, correo, contrasenia, telefono, avatar, direccion, fechaNacimiento, genero, tipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombreCompleto());
            statement.setString(2, usuario.getCorreo());
            statement.setString(3, usuario.getContrasenia());
            statement.setString(4, usuario.getTelefono());
            statement.setString(5, usuario.getAvatar());
            statement.setString(6, usuario.getDireccion());
            statement.setDate(7, usuario.getFechaNacimiento());
            statement.setString(8, usuario.getGenero().toString());
            statement.setString(9, usuario.getTipoUsuario().toString());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0; // Registro exitoso
        } catch (SQLIntegrityConstraintViolationException e) {
            throw e; // Lanzamos la excepción para que sea manejada por el servlet
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificarUsuario(UsuarioDTO usuario, String nuevaContrasenia) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean actualizado = false;

        try {
            conn = ConexionBD.getConnection();
            String sql = "UPDATE Usuarios SET nombreCompleto = ?, correo = ?, telefono = ?, direccion = ?, fechaNacimiento = ?, genero = ?, avatar = ?"
                    + (nuevaContrasenia != null && !nuevaContrasenia.isEmpty() ? ", contrasenia = ?" : "")
                    + " WHERE id = ?";

            stmt = conn.prepareStatement(sql);

            // Establecer parámetros
            stmt.setString(1, usuario.getNombreCompleto());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getTelefono());
            stmt.setString(4, usuario.getDireccion());
            stmt.setDate(5, usuario.getFechaNacimiento());
            stmt.setString(6, usuario.getGenero().name());
            stmt.setString(7, usuario.getAvatar());

            int index = 8;
            if (nuevaContrasenia != null && !nuevaContrasenia.isEmpty()) {
                stmt.setString(index++, Encriptar.encriptar(nuevaContrasenia));
            }

            stmt.setLong(index, usuario.getId());

            // Ejecutar actualización
            actualizado = stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return actualizado;
    }

    @Override
    public void eliminarUsuario(UsuarioDTO usuario) {
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
        String sql = "SELECT * FROM usuarios WHERE id = ?";
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
                usuario.setDireccion(resultSet.getString("direccion"));
                usuario.setAvatar(resultSet.getString("avatar"));
                usuario.setContrasenia(resultSet.getString("contrasenia"));
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
        String sql = "SELECT * FROM usuarios WHERE correo = ?";

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, correo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setId(resultSet.getLong("id"));
                usuario.setNombreCompleto(resultSet.getString("nombreCompleto"));
                usuario.setCorreo(resultSet.getString("correo"));

                // Guardar la contraseña encriptada directamente
                usuario.setContrasenia(resultSet.getString("contrasenia"));
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

    public UsuarioDTO validarLogin(String correo, String contraseniaIngresada) throws Exception {
        Usuario usuario = obtenerUsuarioPorCorreo(correo);

        if (usuario != null) {
            // Encriptar la contraseña ingresada para compararla con la almacenada
            String contraseniaIngresadaEncriptada = Encriptar.encriptar(contraseniaIngresada);

            // Comparar la contraseña encriptada
            if (usuario.getContrasenia().equals(contraseniaIngresadaEncriptada)) {
                // Convertir Usuario a UsuarioDTO y devolverlo
                return mapeos.usuarioToDTO(usuario);
            }
        }

        return null; // Retornar null si no coincide o no existe
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
