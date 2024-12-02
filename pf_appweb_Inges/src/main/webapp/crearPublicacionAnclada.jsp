<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dtos.UsuarioDTO" %>
<%
    // Obtener el usuario de la sesión
    UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
    if (usuario == null) {
        // Redirigir al login si no hay sesión
        response.sendRedirect("login.jsp");
        return;
    } else if (!usuario.getTipoUsuario().toString().equalsIgnoreCase("Admor")) {
        // Redirigir a publicaciones.jsp si el usuario no es administrador
        response.sendRedirect("PublicacionesServlet");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/styleCrearPublicacionAnclada.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
    <title>Crear Publicación Anclada</title>
</head>
<body>
    <header>
        <h1>Crear Publicación Anclada</h1>
        <nav>
            <a href="PublicacionesServlet" class="exit-btn"><i class="fa-solid fa-arrow-left"></i></a>
        </nav>
    </header>
    <main class="container">
        <section class="form-container">
            <h2>¡Comparte algo importante!</h2>
            <form action="createPostServlet" method="POST">
                <div class="form-group">
                    <label for="titulo">Título</label>
                    <input type="text" id="titulo" name="titulo" placeholder="Título de la publicación" required>
                </div>
                <div class="form-group">
                    <label for="contenido">Contenido</label>
                    <textarea id="contenido" name="contenido" rows="8" placeholder="Escribe aquí el contenido..." required></textarea>
                </div>
                <!-- Campo oculto para indicar que esta publicación es anclada -->
                <input type="hidden" name="esAnclado" value="true">
                <div class="form-group">
                    <button type="submit" class="submit-btn"><i class="fa-solid fa-paper-plane"></i> Publicar</button>
                </div>
            </form>
        </section>
    </main>
</body>
</html>
