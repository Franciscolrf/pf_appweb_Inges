/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import negocio.PostBO;
import com.google.gson.JsonObject;
import daos.PostDAO;
import dtos.PostDTO;
import dtos.UsuarioDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author franc
 */
@WebServlet(name = "createPostServlet", urlPatterns = {"/createPostServlet"})
public class createPostServlet extends HttpServlet {

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
            out.println("<title>Servlet createPostServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet createPostServlet at " + request.getContextPath() + "</h1>");
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
            // Obtener los parámetros del formulario
            String titulo = request.getParameter("titulo");
            String contenido = request.getParameter("contenido");
            String esAncladoParam = request.getParameter("esAnclado");

            // Determinar si es una publicación anclada (por defecto es falsa)
            boolean esAnclado = "true".equalsIgnoreCase(esAncladoParam);

            UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("usuario");
            if (usuario == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Crear el objeto PostDTO
            PostDTO post = new PostDTO();
            post.setTitulo(titulo);
            post.setContenido(contenido);
            post.setUsuario(usuario);
            post.setAnclado(esAnclado); // Establecer si la publicación es anclada

            // Guardar la publicación utilizando el BO
            PostBO postBO = new PostBO();
            boolean exito = postBO.agregarPost(post);

            if (exito) {
                // Redirigir al servlet de publicaciones
                response.sendRedirect("PublicacionesServlet");
            } else {
                request.setAttribute("mensaje", "Error al crear la publicación.");
                request.setAttribute("tipoMensaje", "error");
                request.getRequestDispatcher("crearPublicaciones.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Ocurrió un error al procesar la solicitud.");
            request.setAttribute("tipoMensaje", "error");
            request.getRequestDispatcher("crearPublicaciones.jsp").forward(request, response);
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
