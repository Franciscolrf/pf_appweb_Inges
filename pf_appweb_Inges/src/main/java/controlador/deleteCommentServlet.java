/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import fachada.FachadaApp;
import fachada.IFachadaApp;
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
@WebServlet(name = "deleteCommentServlet", urlPatterns = {"/deleteCommentServlet"})
public class deleteCommentServlet extends HttpServlet {

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
            out.println("<title>Servlet deleteCommentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteCommentServlet at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comentarioIdParam = request.getParameter("comentarioId");

        try {
            if (comentarioIdParam != null) {
                long comentarioId = Long.parseLong(comentarioIdParam);
                IFachadaApp fachada = new FachadaApp();
                boolean isDeleted = fachada.eliminarComentario(comentarioId);

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

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String comentarioIdParam = request.getParameter("comentarioId");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            if (comentarioIdParam != null) {
                long comentarioId = Long.parseLong(comentarioIdParam);
                IFachadaApp fachada = new FachadaApp();
                boolean isDeleted = fachada.eliminarComentario(comentarioId);

                if (isDeleted) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("{\"message\": \"Comentario eliminado exitosamente.\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"No se encontró el comentario.\"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"ID de comentario inválido.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Error interno del servidor.\"}");
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
