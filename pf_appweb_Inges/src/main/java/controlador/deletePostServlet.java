/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import daos.PostDAO;
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
@WebServlet(name = "deletePostServlet", urlPatterns = {"/deletePostServlet"})
public class deletePostServlet extends HttpServlet {

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
            out.println("<title>Servlet deletePostServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deletePostServlet at " + request.getContextPath() + "</h1>");
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
                PostDAO postDAO = new PostDAO();

                boolean isDeleted = postDAO.eliminarPost(postId);

                if (isDeleted) {
                    response.sendRedirect("PublicacionesServlet");
                } else {
                    response.sendRedirect("PublicacionesServlet?error=notFound");
                }
            } else {
                response.sendRedirect("PublicacionesServlet?error=invalidId");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("PublicacionesServlet?error=internalError");
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String postIdParam = request.getParameter("postId");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            if (postIdParam != null) {
                long postId = Long.parseLong(postIdParam);
                PostDAO postDAO = new PostDAO();

                boolean isDeleted = postDAO.eliminarPost(postId);

                if (isDeleted) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("{\"message\": \"Post eliminado exitosamente.\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"No se encontró el post.\"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"ID de post inválido.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Error interno del servidor.\"}");
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
