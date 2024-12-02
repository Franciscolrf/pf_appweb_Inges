/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import conversor.Mapeos;
import daos.UsuarioDAO;
import dtos.UsuarioDTO;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author af_da
 */
public class UsuarioBO {

    private UsuarioDAO usuarioDAO;
    private Mapeos mapeos;

    public UsuarioBO() {
        usuarioDAO = new UsuarioDAO();
        this.mapeos = new Mapeos();
    }

    // Método para registrar un usuario
    public boolean registrarUsuario(UsuarioDTO usuario) {
        try {
            // Validación de los datos del usuario
            validarDatosUsuario(usuario);

            // Llamada al método DAO para registrar el usuario
            return usuarioDAO.registrarUsuario(mapeos.dtoToUsuario(usuario));
        } catch (SQLIntegrityConstraintViolationException e) {
            // Manejo de excepciones específicas de integridad (como duplicados)
            System.out.println("Error: Usuario ya existe en el sistema.");
            return false;
        } catch (Exception e) {
            // Manejo de otros errores generales
            e.printStackTrace();
            return false;
        }
    }

    // Método para modificar un usuario
    public boolean modificarUsuario(UsuarioDTO usuario, String nuevaContrasenia) {
        try {
            // Validación de los datos del usuario
            validarDatosUsuario(usuario);

            // Llamada al método DAO para modificar el usuario
            return usuarioDAO.modificarUsuario(mapeos.dtoToUsuario(usuario), nuevaContrasenia);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un usuario
    public boolean eliminarUsuario(long idUsuario) {
        try {
            // Llamada al método DAO para eliminar el usuario
            return usuarioDAO.eliminarUsuario(idUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener un usuario por ID
    public UsuarioDTO obtenerUsuarioPorId(int id) {
        try {
            // Llamada al método DAO para obtener el usuario
            return mapeos.usuarioToDTO(usuarioDAO.obtenerUsuario(id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para obtener un usuario por correo
    public UsuarioDTO obtenerUsuarioPorCorreo(String correo) {
        try {
            // Llamada al método DAO para obtener el usuario por correo
            return mapeos.usuarioToDTO(usuarioDAO.obtenerUsuarioPorCorreo(correo));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para validar el login
    public UsuarioDTO validarLogin(String correo, String contrasenia) {
        try {
            // Llamada al método DAO para validar el login
            return mapeos.usuarioToDTO(usuarioDAO.validarLogin(correo, contrasenia));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método privado para validar los datos del usuario
    private void validarDatosUsuario(UsuarioDTO usuario) {
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
        if (usuario.getDireccion() == null || usuario.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria.");
        }
    }
}
