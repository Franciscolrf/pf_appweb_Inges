<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dtos.UsuarioDTO" %>
<%
    // Verificar si el usuario está en sesión
    UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/styleCrearPublicaciones.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
    <title>Crear Publicación</title>
</head>
<body>
    <header>
        <h1>Crear Publicación</h1>
        <nav>
            <a href="publicaciones.jsp" class="exit-btn"><i class="fa-solid fa-arrow-left"></i></a>
        </nav>
    </header>
    <main class="container">
        <section class="form-container">
            <h2>¡Comparte tus ideas!</h2>
            <form action="crearPostServlet" method="POST">
                <div class="form-group">
                    <label for="titulo">Título</label>
                    <input type="text" id="titulo" name="titulo" placeholder="Título de la publicación" required>
                </div>
                <div class="form-group">
                    <label for="contenido">Contenido</label>
                    <textarea id="contenido" name="contenido" rows="8" placeholder="Escribe aquí el contenido..." required></textarea>
                </div>
                <% if (usuario.getTipoUsuario().toString().equals("Admor")) { %>
                    <div class="form-group">
                        <label for="anclado">¿Publicación anclada?</label>
                        <input type="checkbox" id="anclado" name="anclado">
                    </div>
                <% } %>
                <div class="form-group">
                    <button type="submit" class="submit-btn"><i class="fa-solid fa-paper-plane"></i> Publicar</button>
                </div>
            </form>
        </section>
    </main>
</body>
</html>
