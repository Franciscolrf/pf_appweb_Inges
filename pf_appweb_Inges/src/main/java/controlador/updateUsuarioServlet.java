/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import daos.UsuarioDAO;
import dtos.UsuarioDTO;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapeos.Encriptar;
import conversor.Mapeos;
import modelo.Genero;
import modelo.Usuario;
import negocio.UsuarioBO;

/**
 *
 * @author Fran
 */
@WebServlet(name = "updateUsuarioServlet", urlPatterns = {"/updateUsuarioServlet"})
@MultipartConfig
public class updateUsuarioServlet extends HttpServlet {
    
private static final String UPLOAD_DIRECTORY = "uploads";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateUsuarioServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateUsuarioServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String mensaje = "Cambios realizados con éxito.";
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        boolean nombreActualizado= false, correoActualizado= false, telefonoActualizado= false, direccionActualizado = false, contraseñaActualizada=false;

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Obtener valores actuales del usuario de la base de datos
        UsuarioBO usuarioBO = new UsuarioBO();
        UsuarioDTO usuarioActualizado = usuarioBO.obtenerUsuarioPorId(usuario.getId().intValue());

        // Obtener datos del formulario
        String nombreCompletoFormulario = request.getParameter("nombreCompleto");
        if (nombreCompletoFormulario != null && !nombreCompletoFormulario.trim().isEmpty()
                && (usuarioActualizado.getNombreCompleto() == null
                || !usuarioActualizado.getNombreCompleto().equals(nombreCompletoFormulario))) {
            usuario.setNombreCompleto(nombreCompletoFormulario);
             nombreActualizado = true;
        }

        String correoFormulario = request.getParameter("correo");
        if (correoFormulario != null && !correoFormulario.trim().isEmpty()
                && (usuarioActualizado.getCorreo() == null
                || !usuarioActualizado.getCorreo().equals(correoFormulario))) {
            usuario.setCorreo(correoFormulario);
             correoActualizado = true;
        }

        String telefonoFormulario = request.getParameter("telefono");

        if (telefonoFormulario != null && !telefonoFormulario.trim().isEmpty()) {
            telefonoFormulario = telefonoFormulario.trim(); // Normaliza eliminando espacios extra

            // Comprueba si el teléfono actual es diferente al proporcionado
            if (usuarioActualizado.getTelefono() == null
                    || !usuarioActualizado.getTelefono().trim().equals(telefonoFormulario)) {

                usuario.setTelefono(telefonoFormulario);
                telefonoActualizado = true;
            }
        }

        String direccionFormulario = request.getParameter("direccion");
        if (direccionFormulario != null && !direccionFormulario.trim().isEmpty()
                && (usuarioActualizado.getDireccion() == null
                || !usuarioActualizado.getDireccion().equals(direccionFormulario))) {
            usuario.setDireccion(direccionFormulario);
             direccionActualizado = true;
        }


        String fechaNacimientoFormulario = request.getParameter("fechaNacimiento");
        if (fechaNacimientoFormulario != null && !fechaNacimientoFormulario.isEmpty()
                && (usuarioActualizado.getFechaNacimiento() == null
                || !usuarioActualizado.getFechaNacimiento().equals(Date.valueOf(fechaNacimientoFormulario)))) {
            usuario.setFechaNacimiento(Date.valueOf(fechaNacimientoFormulario));
        }

        String generoFormulario = request.getParameter("genero");
        if (generoFormulario != null && !generoFormulario.trim().isEmpty()
                && (usuarioActualizado.getGenero() == null
                || !usuarioActualizado.getGenero().equals(Genero.valueOf(generoFormulario.toUpperCase())))) {
            usuario.setGenero(Genero.valueOf(generoFormulario.toUpperCase()));
        }

        Part avatarPart = request.getPart("avatar");
        String avatarPath = null;
        if (avatarPart != null && avatarPart.getSize() > 0) {
            String fileName = avatarPart.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            avatarPath = "uploads/" + fileName;
            avatarPart.write(uploadPath + File.separator + fileName);

            if (usuarioActualizado.getAvatar() == null || !avatarPath.equals(usuarioActualizado.getAvatar())) {
                usuario.setAvatar(avatarPath);
            }
        } else {
            usuario.setAvatar(usuarioActualizado.getAvatar());
        }

        // Nueva contraseña solo si el campo no está vacío
        String nuevaContrasenia = request.getParameter("contrasenia");
        Encriptar enc = new Encriptar();
        String contraseniaFormularioEncriptada = null;
        String contraseniaUser = usuarioActualizado.getContrasenia();
        if (nuevaContrasenia != null && !nuevaContrasenia.trim().isEmpty()) {
            try {
                contraseniaFormularioEncriptada = Encriptar.encriptar(nuevaContrasenia);
            } catch (Exception ex) {
                Logger.getLogger(updateUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (!contraseniaFormularioEncriptada.equals(contraseniaUser)) {
                    usuario.setContrasenia(nuevaContrasenia);
                    contraseñaActualizada =true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Llamar al método modificarUsuario en UsuarioDAO
        boolean actualizado = usuarioBO.modificarUsuario(usuario, nuevaContrasenia);

        // Configuración del mensaje de respuesta
        
              if (nombreActualizado == true) {
            mensaje += " Nombre actualizado.";
        }
        if (correoActualizado == true) {
            mensaje += " Correo actualizado.";
        }
        if (telefonoActualizado == true) {
            mensaje += " Teléfono actualizado.";
        }
        if (direccionActualizado == true) {
            mensaje += " Dirección actualizada.";
        }
        if (usuarioActualizado.getFechaNacimiento() == null
                || !usuarioActualizado.getFechaNacimiento().equals(usuario.getFechaNacimiento())) {
            mensaje += " Fecha de nacimiento actualizada.";
        }
        if (usuarioActualizado.getGenero() == null
                || !usuarioActualizado.getGenero().equals(usuario.getGenero())) {
            mensaje += " Género actualizado.";
        }
        if (usuarioActualizado.getAvatar() == null
                || !usuarioActualizado.getAvatar().equals(usuario.getAvatar())) {
            mensaje += " Avatar actualizado.";
        }

        if (contraseñaActualizada = true) {
            mensaje += " Contraseña actualizada.";
        }

        

        // Enviar mensaje al JSP
        if (actualizado) {
            session.setAttribute("usuario", usuario); // Actualizar el usuario en sesión
            request.setAttribute("mensaje", mensaje);
            request.setAttribute("tipoMensaje", "success");
        } else {
            request.setAttribute("mensaje", "Error al realizar los cambios.");
            request.setAttribute("tipoMensaje", "error");
        }

        request.getRequestDispatcher("configUsuario.jsp").forward(request, response);
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
