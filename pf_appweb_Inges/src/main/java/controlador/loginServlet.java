/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import daos.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapeos.Encriptar;
import modelo.Usuario;

/**
 *
 * @author Fran
 */
@WebServlet(name = "loginServlet", urlPatterns = { "/loginServlet" })
public class loginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Encriptar encriptar = new Encriptar();

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         // Obtener parámetros de correo y contraseña
        String correo = request.getParameter("correo");
        String contrasenia = request.getParameter("contrasenia");

        // Llamar al método de autenticación en UsuarioDAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = null;
        try {
            usuario = usuarioDAO.obtenerUsuarioPorCorreo(correo);
        } catch (Exception ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

       // Verificar que el usuario existe.

        if (usuario != null) {
            try {
                // Desencriptar la contraseña almacenada en la base de datos
                String contraseniaDesencriptada = Encriptar.desencriptar(usuario.getContrasenia());

                // Verificar que la contraseña ingresada por el usuario es correcta
                if (contrasenia.equals(contraseniaDesencriptada)) {
                    // Crear una sesión para el usuario
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", usuario);

                    // Redirigir al usuario a la página de inicio
                    response.sendRedirect("index.html");
                } else {
                    // Si la contraseña es incorrecta, redirigir al usuario a la página de inicio de sesión
                    response.sendRedirect("login.html");
                }
            } catch (Exception ex) {
                Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Si el usuario no existe, redirigir al usuario a la página de inicio de sesión
            response.sendRedirect("login.html");
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
