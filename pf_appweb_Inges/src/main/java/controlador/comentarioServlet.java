/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import negocio.PostBO;
import dtos.ComentarioDTO;
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
import java.io.BufferedReader;

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject responseJson = new JsonObject();

        try {
            // Leer el cuerpo de la solicitud
            BufferedReader reader = request.getReader();
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            String jsonString = jsonBuilder.toString();

            // Parsear el JSON
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

            // Extraer los parámetros del JSON
            if (jsonObject == null || !jsonObject.has("postId") || !jsonObject.has("contenido")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                responseJson.addProperty("success", false);
                responseJson.addProperty("message", "Parámetros inválidos.");
                response.getWriter().write(responseJson.toString());
                return;
            }

            long postId = jsonObject.get("postId").getAsLong();
            String contenido = jsonObject.get("contenido").getAsString();

            // Obtener el usuario actual de la sesión
            UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("usuario");
            if (usuario == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                responseJson.addProperty("success", false);
                responseJson.addProperty("message", "Usuario no autenticado.");
                response.getWriter().write(responseJson.toString());
                return;
            }

            // Obtener el PostDTO asociado al comentario
            PostBO postBO = new PostBO();
            PostDTO post = postBO.obtenerPostPorId(postId);

            if (post == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                responseJson.addProperty("success", false);
                responseJson.addProperty("message", "El post no existe.");
                response.getWriter().write(responseJson.toString());
                return;
            }

            // Crear el objeto ComentarioDTO
            ComentarioDTO comentario = new ComentarioDTO();
            comentario.setContenido(contenido);
            comentario.setUsuario(usuario);
            comentario.setPost(post);

            // Verificar que todos los campos necesarios estén presentes
            if (comentario.getPost() == null || comentario.getUsuario() == null || comentario.getContenido() == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                responseJson.addProperty("success", false);
                responseJson.addProperty("message", "El comentario no tiene todos los campos requeridos.");
                response.getWriter().write(responseJson.toString());
                return;
            }

            // Guardar el comentario usando la fachada.
            IFachadaApp fachada = new FachadaApp();
            boolean exito = fachada.agregarComentario(comentario);

            if (exito) {
                response.setStatus(HttpServletResponse.SC_OK);
                responseJson.addProperty("success", true);
                responseJson.addProperty("message", "Comentario agregado exitosamente.");
                response.getWriter().write(responseJson.toString());
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                responseJson.addProperty("success", false);
                responseJson.addProperty("message", "Error al agregar el comentario.");
                response.getWriter().write(responseJson.toString());
            }
        } catch (JsonSyntaxException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseJson.addProperty("success", false);
            responseJson.addProperty("message", "Error al parsear el JSON: " + e.getMessage());
            response.getWriter().write(responseJson.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseJson.addProperty("success", false);
            responseJson.addProperty("message", "Ocurrió un error al procesar la solicitud.");
            response.getWriter().write(responseJson.toString());
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
