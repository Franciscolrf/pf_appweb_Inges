/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import bo.PostBO;
import daos.PostDAO;
import dtos.PostDTO;
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
@WebServlet(name = "editPostServlet", urlPatterns = {"/editPostServlet"})
public class editPostServlet extends HttpServlet {

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
            out.println("<title>Servlet editPostServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editPostServlet at " + request.getContextPath() + "</h1>");
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
        String postIdParam = request.getParameter("postId");

        try {
            if (postIdParam != null) {
                long postId = Long.parseLong(postIdParam);
                PostBO postBO = new PostBO();
                PostDTO postDTO = postBO.obtenerPostPorId(postId);

                if (postDTO != null) {
                    request.setAttribute("post", postDTO);
                    request.getRequestDispatcher("editarPublicacion.jsp").forward(request, response);
                } else {
                    response.sendRedirect("PublicacionesServlet?error=postNotFound");
                }
            } else {
                response.sendRedirect("PublicacionesServlet?error=invalidId");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("PublicacionesServlet?error=internalError");
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
        String idParam = request.getParameter("postId");
        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");

        try {
            // Validar que todos los parámetros están presentes
            if (idParam == null || titulo == null || contenido == null) {
                response.sendRedirect("PublicacionesServlet?error=missingParameters");
                return;
            }

            long id = Long.parseLong(idParam);

            PostBO postBO = new PostBO();
            PostDTO postDTO = postBO.obtenerPostPorId(id);

            if (postDTO.isAnclado()) {
                response.sendRedirect("editarPublicacion.jsp?postId=" + postDTO.getId() + "&error=notEditable");
                return;
            }

            postDTO.setTitulo(titulo);
            postDTO.setContenido(contenido);

            boolean isUpdated = postBO.modificarPost(postDTO);

            if (isUpdated) {
                response.sendRedirect("PublicacionesServlet?success=postUpdated");
            } else {
                response.sendRedirect("PublicacionesServlet?error=updateFailed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("PublicacionesServlet?error=internalError");
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
