<%@page import="dtos.PostDTO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    PostDTO post = (PostDTO) request.getAttribute("post");
    if (post == null) {
        response.sendRedirect("PublicacionesServlet?error=postNotFound");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/styleEditarPublicaciones.css">
        <title>Editar Publicación</title>
    </head>
    <body>
        <div class="form-container">
            <h2>Editar Publicación</h2>
            <form id="editPostForm">
                <input type="hidden" name="postId" value="<%= post.getId()%>">
                <div class="form-group">
                    <label for="titulo">Título</label>
                    <input type="text" id="titulo" name="titulo" value="<%= post.getTitulo()%>" required>
                </div>
                <div class="form-group">
                    <label for="contenido">Contenido</label>
                    <textarea id="contenido" name="contenido" required><%= post.getContenido()%></textarea>
                </div>

                <% if (!post.isAnclado()) { %>
                <div class="form-actions">
                    <button type="button" class="submit-btn">
                        <i class="fa-solid fa-save"></i> Guardar Cambios
                    </button>
                    <a href="PublicacionesServlet" class="cancel-btn">
                        <i class="fa-solid fa-ban"></i> Cancelar
                    </a>
                </div>
                <% } else { %>
                <p style="color: red; text-align: center;">Esta publicación está anclada y no puede ser editada.</p>
                <% }%>
            </form>
        </div>

        <script src="scripts/editarPublicacion.js"></script>
    </body>
</html>
