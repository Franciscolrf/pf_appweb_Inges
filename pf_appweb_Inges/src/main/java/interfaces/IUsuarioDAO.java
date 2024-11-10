/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.UsuarioDTO;
import modelo.Usuario;

/**
 *
 * @author Fran
 */
public interface IUsuarioDAO {
    void registrarUsuario(UsuarioDTO usuario);
    boolean modificarUsuario(UsuarioDTO usuario, String nuevaContrasenia);
    void eliminarUsuario(UsuarioDTO usuario);
    Usuario obtenerUsuario(int id);
}
