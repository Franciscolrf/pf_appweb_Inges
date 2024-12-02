package interfaces;

import dtos.UsuarioDTO;
import modelo.Usuario;

import java.sql.SQLIntegrityConstraintViolationException;

public interface IUsuarioBO {

    // Método para registrar un nuevo usuario
    boolean registrarUsuario(UsuarioDTO usuario) throws SQLIntegrityConstraintViolationException;

    // Método para modificar un usuario
    boolean modificarUsuario(UsuarioDTO usuario, String nuevaContrasenia);

    // Método para eliminar un usuario
    boolean eliminarUsuario(long idUsuario);

    // Método para obtener un usuario por su ID
    Usuario obtenerUsuario(int id);

    // Método para obtener un usuario por su correo
    Usuario obtenerUsuarioPorCorreo(String correo) throws Exception;

    // Método para validar el login de un usuario
    UsuarioDTO validarLogin(String correo, String contraseniaIngresada) throws Exception;
}
