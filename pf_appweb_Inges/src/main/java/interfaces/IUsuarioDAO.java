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
    boolean registrarUsuario(UsuarioDTO usuario)throws SQLIntegrityConstraintViolationException;
    void modificarUsuario(Usuario usuario);
    void eliminarUsuario(Usuario usuario);
    Usuario obtenerUsuario(int id);
}
