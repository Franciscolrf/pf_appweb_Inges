<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dtos.UsuarioDTO" %>
<%
    // Obtener el usuario de la sesión
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
    <link rel="stylesheet" href="styles/stylePublicaciones.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
    <title>Publicaciones</title>
</head>
<body>
    <header>
        <h1>Publicaciones</h1>
        <nav>
            <a href="index.html" class="exit-btn"><i class="fa-solid fa-xmark"></i></a>
        </nav>
    </header>
    <main class="container">
        <aside class="sidebar">
            <div class="user-info">
                <img src="<%= usuario.getAvatar() != null ? usuario.getAvatar() : "uploads/default.png" %>" alt="Avatar del usuario" class="avatar">
                <h3><%= usuario.getNombreCompleto() %></h3>
                <% if (usuario.getTipoUsuario().toString().equalsIgnoreCase("Admor")) { %>
                    <p style="color: red; font-weight: bold;">Administrador</p>
                <% } %>
            </div>
            <ul class="sidebar-links">
                <li><a href="configUsuario.jsp"><i class="fa-solid fa-gear"></i> Configuración</a></li>
                
                    <% if (usuario.getTipoUsuario().toString().equalsIgnoreCase("Admor")) { %>
                        <li><a href="crearPublicacionesAncladas.jsp" class="create-btn"><i class="fa-solid fa-thumbtack"></i> Crear publicación anclada</a> </li>
                    <% } %>
                
                <li><a href="crearPublicaciones.jsp" class="create-btn"><i class="fa-solid fa-plus"></i> Crear publicación</a></li>
                <li><a href="logoutServlet"><i class="fa-solid fa-xmark"></i> Cerrar Sesión</a></li>
            </ul>
        </aside>
        <section class="content">
            <section class="pinned-posts">
                <h2>Publicaciones Ancladas</h2>
                <div class="post-container">
                    <div class="post">
                        <h3>¿Cuál es tu serie favorita de todos los tiempos?</h3>
                        <p>Comparte con nosotros tus recomendaciones de series que no te puedes perder.</p>
                    </div>
                    <div class="post">
                        <h3>La mejor serie del 2024</h3>
                        <p>¿Qué serie crees que marcará el 2024? Cuéntanos tu opinión.</p>
                    </div>
                </div>
            </section>

            <section class="published-posts">
                <h2>Publicaciones Recientes</h2>
                <div class="post-container">
                    <div class="post">
                        <h3>El regreso de Stranger Things</h3>
                        <p>La serie regresa con nuevas aventuras en su próxima temporada.</p>
                        <div class="post-actions">
                            <% if (usuario.getTipoUsuario().toString().equalsIgnoreCase("Admor")) { %>
                                <button class="edit-btn"><i class="fa-solid fa-pencil"></i> Editar</button>
                                <button class="delete-btn"><i class="fa-solid fa-trash"></i> Eliminar</button>
                            <% } %>
                        </div>
                        <div class="comments">
                            <h4>Comentarios:</h4>
                            <div class="comment">
                                <p>¡Estoy tan emocionado por el regreso! 👏</p>
                                <span class="comment-time">Hace 2 horas</span>
                            </div>
                            <div class="comment">
                                <p>Siempre amé esta serie, no puedo esperar a verla.</p>
                                <span class="comment-time">Hace 1 hora</span>
                            </div>
                        </div>
                    </div>

                    <div class="post">
                        <h3>Las mejores series de comedia</h3>
                        <p>Una lista de las series de comedia más divertidas para disfrutar.</p>
                        <div class="post-actions">
                            <% if (usuario.getTipoUsuario().toString().equalsIgnoreCase("Admor")) { %>
                                <button class="edit-btn"><i class="fa-solid fa-pencil"></i> Editar</button>
                                <button class="delete-btn"><i class="fa-solid fa-trash"></i> Eliminar</button>
                            <% } %>
                        </div>
                        <div class="comments">
                            <h4>Comentarios:</h4>
                            <div class="comment">
                                <p>¡Me encanta "Brooklyn Nine-Nine"! 😂</p>
                                <span class="comment-time">Hace 30 minutos</span>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </section>
    </main>
</body>
</html>
