/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.sql.SQLIntegrityConstraintViolationException;
import modelo.Usuario;

/**
 *
 * @author Fran
 */
public interface IUsuarioDAO {

    boolean modificarUsuario(Usuario usuario, String nuevaContrasenia);
    boolean eliminarUsuario(long id);
    boolean registrarUsuario(Usuario usuario)throws SQLIntegrityConstraintViolationException;
    Usuario obtenerUsuario(int id);
}
