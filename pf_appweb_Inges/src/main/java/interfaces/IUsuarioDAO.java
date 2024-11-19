/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.UsuarioDTO;
import java.sql.SQLIntegrityConstraintViolationException;
import modelo.Usuario;

/**
 *
 * @author Fran
 */
public interface IUsuarioDAO {

    boolean modificarUsuario(UsuarioDTO usuario, String nuevaContrasenia);
    void eliminarUsuario(UsuarioDTO usuario);
    boolean registrarUsuario(UsuarioDTO usuario)throws SQLIntegrityConstraintViolationException;
    Usuario obtenerUsuario(int id);
}
