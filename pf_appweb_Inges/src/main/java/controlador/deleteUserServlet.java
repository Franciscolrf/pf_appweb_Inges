/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import com.google.gson.JsonObject;
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
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fran
 */
@WebServlet(name = "deleteUserServlet", urlPatterns = {"/deleteUserServlet"})
public class deleteUserServlet extends HttpServlet {

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
            out.println("<title>Servlet deleteUserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteUserServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");

        if (usuario == null) {
            // Si no hay sesión, redirigir al login
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Llamar al método de eliminación en la Fachada
            IFachadaApp fachada = new FachadaApp();
            boolean eliminado = fachada.eliminarUsuario(usuario.getId());


            if (eliminado) {
                // Si se eliminó correctamente, invalidar la sesión
                session.invalidate();
                response.sendRedirect("login.jsp?status=accountDeleted");
            } else {
                // Si hubo un error, redirigir con un mensaje de error
                response.sendRedirect("configUsuario.jsp?status=deleteError");
            }
        } catch (Exception ex) {
            Logger.getLogger(deleteUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("configUsuario.jsp?status=deleteError");
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        JsonObject responseJson = new JsonObject();

        if (usuario == null) {
            // Si no hay sesión, responder con error de autenticación
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            responseJson.addProperty("error", "Usuario no autenticado.");
            response.getWriter().write(responseJson.toString());
            return;
        }

        try {
            // Llamar al método de eliminación en la Fachada
            IFachadaApp fachada = new FachadaApp();
            boolean eliminado = fachada.eliminarUsuario(usuario.getId());

            if (eliminado) {
                // Si se eliminó correctamente, invalidar la sesión
                session.invalidate();
                response.setStatus(HttpServletResponse.SC_OK);
                responseJson.addProperty("success", true);
                responseJson.addProperty("message", "Usuario eliminado con éxito.");
            } else {
                // Si hubo un error al eliminar el usuario
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                responseJson.addProperty("success", false);
                responseJson.addProperty("error", "No se pudo eliminar el usuario.");
            }
        } catch (Exception ex) {
            // Manejo de errores del servidor
            Logger.getLogger(deleteUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseJson.addProperty("error", "Ocurrió un error inesperado al eliminar el usuario.");
        }

        // Enviar la respuesta JSON
        response.getWriter().write(responseJson.toString());
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
