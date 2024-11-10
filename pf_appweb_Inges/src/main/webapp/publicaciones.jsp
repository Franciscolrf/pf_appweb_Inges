<%@page import="dtos.UsuarioDTO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Recuperar el usuario desde la sesión
    UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
    if (usuario == null) {
        // Redirigir al login si no hay sesión activa
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/stylePublicaciones.css">
    <title>Publicaciones</title>
</head>
<body>
    <main class="container">
        <aside class="sidebar">
            <div class="user-info">
                <img src="<%= usuario.getAvatar() != null ? usuario.getAvatar() : 'uploads/default.png' %>" alt="Avatar del usuario" class="avatar">
                <p><%= usuario.getNombreCompleto() %></p>
            </div>
            <nav class="navigation">
                <ul>
                    <li><a href="#">Configuración</a></li>
                    <li><a href="logoutServlet">Cerrar Sesión</a></li>
                    <li><a href="#">Crear publicación</a></li>
                </ul>
            </nav>
        </aside>
        <!-- Aquí irá el contenido de publicaciones -->
        <section class="main-content">
            <h1>Publicaciones</h1>
            <!-- Aquí se agregarán las publicaciones dinámicamente en el futuro -->
        </section>
    </main>
</body>
</html>


