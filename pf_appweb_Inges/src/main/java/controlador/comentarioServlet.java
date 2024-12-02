/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import negocio.ComentarioBO;
import negocio.PostBO;
import daos.ComentarioDAO;
import daos.PostDAO;
import dtos.ComentarioDTO;
import dtos.PostDTO;
import dtos.UsuarioDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author franc
 */
@WebServlet(name = "comentarioServlet", urlPatterns = {"/comentarioServlet"})
public class comentarioServlet extends HttpServlet {

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
            out.println("<title>Servlet comentarioServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet comentarioServlet at " + request.getContextPath() + "</h1>");
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
         try {
            // Obtener el ID del post y el contenido del comentario
            String postIdParam = request.getParameter("postId");
            String contenido = request.getParameter("contenido");

            if (postIdParam == null || contenido == null || contenido.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Parámetros inválidos.");
                return;
            }

            long postId = Long.parseLong(postIdParam);

            // Obtener el usuario actual de la sesión
            UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("usuario");
            if (usuario == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Usuario no autenticado.");
                return;
            }

            // Obtener el PostDTO asociado al comentario
            PostBO postBO = new PostBO();
            PostDTO post = postBO.obtenerPostPorId(postId);

            if (post == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("El post no existe.");
                return;
            }

            // Crear el objeto ComentarioDTO
            ComentarioDTO comentario = new ComentarioDTO();
            comentario.setContenido(contenido);
            comentario.setUsuario(usuario);
            comentario.setPost(post);

            // Guardar el comentario usando el DAO
            ComentarioBO comentarioBO = new ComentarioBO();
            boolean exito = comentarioBO.agregarComentario(comentario);

            if (exito) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Comentario agregado exitosamente.");
                response.sendRedirect("PublicacionesServlet");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error al agregar el comentario.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Ocurrió un error al procesar la solicitud.");
        }
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
