/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import daos.ComentarioDAO;
import daos.PostDAO;
import dtos.PostDTO;
import dtos.UsuarioDTO;
import fachada.FachadaApp;
import fachada.IFachadaApp;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author franc
 */
@WebServlet(name = "PublicacionesServlet", urlPatterns = {"/PublicacionesServlet"})
public class PublicacionesServlet extends HttpServlet {

    private PostDAO postDAO;
    private ComentarioDAO comentarioDAO;

    @Override
    public void init() throws ServletException {
        postDAO = new PostDAO();
        comentarioDAO = new ComentarioDAO();
    }

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
            out.println("<title>Servlet PublicacionesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PublicacionesServlet at " + request.getContextPath() + "</h1>");
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
        // Obtener el usuario de la sesión
        UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            IFachadaApp fachada = new FachadaApp();
            List<PostDTO> publicaciones = fachada.obtenerTodasLasPublicaciones();
            List<PostDTO> publicacionesAncladas = fachada.obtenerPublicacionesAncladas();

            // Verificar si la solicitud es una llamada Ajax
            String requestedWith = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(requestedWith)) {
                // Responder en JSON para solicitudes Ajax
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                JsonObject responseJson = new JsonObject();
                responseJson.add("usuario", new Gson().toJsonTree(usuario));
                responseJson.add("anclados", new Gson().toJsonTree(publicacionesAncladas));
                responseJson.add("recientes", new Gson().toJsonTree(publicaciones));

                response.getWriter().write(responseJson.toString());
            } else {
                // Devolver la vista JSP
                request.setAttribute("publicaciones", publicaciones);
                request.setAttribute("anclados", publicacionesAncladas);
                request.getRequestDispatcher("publicaciones.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
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
        processRequest(request, response);
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
