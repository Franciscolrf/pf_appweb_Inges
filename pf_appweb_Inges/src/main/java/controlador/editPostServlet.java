/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.PostDTO;
import fachada.FachadaApp;
import fachada.IFachadaApp;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

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
                IFachadaApp fachada = new FachadaApp();
                PostDTO postDTO = fachada.obtenerPostPorId(postId);

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject responseJson = new JsonObject();

        try {
            // Leer los datos enviados en JSON
            BufferedReader reader = request.getReader();
            JsonObject jsonBody = JsonParser.parseReader(reader).getAsJsonObject();

            long postId = jsonBody.get("postId").getAsLong();
            String titulo = jsonBody.get("titulo").getAsString();
            String contenido = jsonBody.get("contenido").getAsString();

             IFachadaApp fachada = new FachadaApp();
                PostDTO postDTO = fachada.obtenerPostPorId(postId);

            if (postDTO == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                responseJson.addProperty("error", "La publicación no existe.");
            } else if (postDTO.isAnclado()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                responseJson.addProperty("error", "Las publicaciones ancladas no pueden ser editadas.");
            } else {
                postDTO.setTitulo(titulo);
                postDTO.setContenido(contenido);
                boolean isUpdated = fachada.modificarPost(postDTO);

                if (isUpdated) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    responseJson.addProperty("success", true);
                    responseJson.addProperty("message", "Publicación actualizada con éxito.");
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    responseJson.addProperty("error", "No se pudo actualizar la publicación.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseJson.addProperty("error", "Ocurrió un error interno en el servidor.");
        }

        response.getWriter().write(responseJson.toString());
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
