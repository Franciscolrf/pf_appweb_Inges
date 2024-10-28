/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package daos;

import java.util.Date;

import modelo.Genero;
import modelo.TipoUsuario;
import modelo.Usuario;

/**
 *
 * @author Fran
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        // Prueba de registro de usuario
        pruebas(usuarioDAO);
    }

    public static void pruebas(UsuarioDAO usuarioDAO) {
        Usuario usuario = new Usuario();
        Date fechaNacimiento = new Date();
        usuario.setId(4L);
        usuario.setNombreCompleto("Juan Pérez");
        usuario.setCorreo("juan.perez@example.com");
        usuario.setContrasenia("password123");
        usuario.setTelefono("1234567890");
        usuario.setAvatar("avatar.png");
        usuario.setGenero(Genero.MASCULINO);
        usuario.setTipoUsuario(TipoUsuario.NORMAL);
        usuario.setFechaNacimiento(fechaNacimiento);

        Usuario usuario3 = new Usuario();
        Date fechaNacimiento3 = new Date();
        usuario3.setId(3L);
        usuario3.setNombreCompleto("Pepe Pérez");
        usuario3.setCorreo("pepe.perez@example.com");
        usuario3.setContrasenia("password123");
        usuario3.setTelefono("1234567890");
        usuario3.setAvatar("avatar.png");
        usuario3.setGenero(Genero.MASCULINO);
        usuario3.setTipoUsuario(TipoUsuario.NORMAL);
        usuario3.setFechaNacimiento(fechaNacimiento3);

        Usuario usuario2 = new Usuario();
        usuario2.setId(4L); 
        usuario2.setNombreCompleto("Juan Pérez Actualizado");
        usuario2.setCorreo("juan.perez@example.com");
        usuario2.setContrasenia("password123");
        usuario2.setTelefono("1234567890");
        usuario2.setAvatar("avatar.png");
        usuario2.setGenero(Genero.MASCULINO);
        usuario2.setTipoUsuario(TipoUsuario.NORMAL);
        Date fechaNacimiento2 = new Date();
        usuario.setFechaNacimiento(fechaNacimiento2);

        usuarioDAO.registrarUsuario(usuario);
        System.out.println("Usuario registrado: " + usuario.getNombreCompleto());

        usuarioDAO.modificarUsuario(usuario);
        System.out.println("Usuario modificado: " + usuario.getNombreCompleto());

        Usuario usuario4 = new Usuario();
        usuario4.setId(3L); 

        usuarioDAO.eliminarUsuario(usuario);
        System.out.println("Usuario eliminado con ID: " + usuario.getId());
    }

   
}


