/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import daos.UsuarioDAO;
import dtos.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapeos.Encriptar;
import modelo.Genero;
import modelo.TipoUsuario;

/**
 *
 * @author Fran
 */

@WebServlet(name = "uploadServlet", urlPatterns = { "/uploadServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class uploadServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "uploads";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

         // Obtención de parámetros del formulario
        String nombreCompleto = request.getParameter("nombreCompleto");
        String correo = request.getParameter("correo");
        String contrasenia = request.getParameter("contrasenia");
        String telefono = request.getParameter("telefono");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String genero = request.getParameter("genero");
        String direccion = request.getParameter("direccion");  

        // Verificar si se ha subido un archivo de avatar
        String avatarPath = null;
        Part filePart = null;
        
        try {
            filePart = request.getPart("avatar");
        } catch (IOException | ServletException ex) {
            Logger.getLogger(uploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (filePart != null) {
            String fileName = getFileName(filePart);

            if (fileName != null && (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))) {

                // Crear el directorio de carga si no existe
                String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                // Guardar el archivo en el servidor
                avatarPath = uploadPath + File.separator + fileName;
                try {
                    filePart.write(avatarPath); // Guardar el archivo
                } catch (IOException ex) {
                    Logger.getLogger(uploadServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                // La ruta que se guardará en la base de datos será la ruta relativa
                avatarPath = UPLOAD_DIRECTORY + "/" + fileName;
            } else {
                response.getWriter().write("El archivo debe ser PNG o JPG.");
                return;
            }
        }

        System.out.println("Ruta del avatar guardada en la base de datos: " + avatarPath);
        // Crear DTO de usuario
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNombreCompleto(nombreCompleto);
        usuario.setCorreo(correo);
        
        // Encriptar la contraseña antes de guardarla
        try {
            usuario.setContrasenia(Encriptar.encriptar(contrasenia));
        } catch (Exception e) {
            Logger.getLogger(uploadServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        
        usuario.setTelefono(telefono);
        usuario.setAvatar(avatarPath);
        usuario.setFechaNacimiento(Date.valueOf(fechaNacimiento));
        usuario.setGenero(Genero.valueOf(genero.toUpperCase()));
        usuario.setTipoUsuario(TipoUsuario.NORMAL);
        usuario.setDireccion(direccion); // Establecer la dirección completa

        // Guardar el usuario en la base de datos
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.registrarUsuario(usuario);

        // Redirigir al login después de un registro exitoso
        response.sendRedirect("login.html");
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
