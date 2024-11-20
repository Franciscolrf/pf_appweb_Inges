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
            <form action="editPostServlet" method="post">
                <input type="hidden" name="postId" value="<%= post.getId()%>">

                <div class="form-group">
                    <label for="titulo">Título</label>
                    <input type="text" id="titulo" name="titulo" value="<%= post.getTitulo()%>" required>
                </div>

                <div class="form-group">
                    <label for="contenido">Contenido</label>
                    <textarea id="contenido" name="contenido" required><%= post.getContenido()%></textarea>
                </div>

                <% if (!post.isAnclado()) {%>
                                <p style="color: red; font-size: 14px;">Esta publicación está anclada y no puede ser editada.</p>
                <% }%>


                <div class="action-btns">
                    <button type="submit" class="save-btn"><i class="fa-solid fa-floppy-disk"></i> Guardar Cambios</button>
                    <a href="PublicacionesServlet" class="cancel-btn"><i class="fa-solid fa-xmark"></i> Cancelar</a>
                </div>
            </form>
        </div>

    </body>
</html>
