<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dtos.UsuarioDTO" %>
<%@ page import="dtos.PostDTO" %>
<%@ page import="dtos.ComentarioDTO" %>
<%@ page import="java.util.List" %>

<%
    // Obtener el usuario de la sesión
    UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Obtener listas de publicaciones y publicaciones ancladas desde los atributos de la solicitud
    List<PostDTO> publicaciones = (List<PostDTO>) request.getAttribute("publicaciones");
    List<PostDTO> anclados = (List<PostDTO>) request.getAttribute("anclados");
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
                <a href="logoutServlet" class="exit-btn"><i class="fa-solid fa-xmark"></i></a>
            </nav>
        </header>
        <main class="container">
            <!-- Barra lateral -->
            <aside class="sidebar">
                <div class="user-info">
                    <img src="<%= usuario.getAvatar() != null ? usuario.getAvatar() : "uploads/default.png"%>" alt="Avatar del usuario" class="avatar">
                    <h3><%= usuario.getNombreCompleto()%></h3>
                    <% if (usuario.getTipoUsuario().toString().equalsIgnoreCase("Admor")) { %>
                    <p style="color: red; font-weight: bold;">Administrador</p>
                    <% } %>
                </div>
                <ul class="sidebar-links">
                    <li><a href="configUsuario.jsp"><i class="fa-solid fa-gear"></i> Configuración</a></li>
                        <% if (usuario.getTipoUsuario().toString().equalsIgnoreCase("Admor")) { %>
                    <li><a href="crearPublicacionAnclada.jsp" class="create-btn"><i class="fa-solid fa-thumbtack"></i> Crear publicación anclada</a></li>
                        <% } %>
                    <li><a href="crearPublicaciones.jsp" class="create-btn"><i class="fa-solid fa-plus"></i> Crear publicación</a></li>
                    <li><a href="logoutServlet"><i class="fa-solid fa-xmark"></i> Cerrar Sesión</a></li>
                </ul>
            </aside>

            <!-- Contenido principal -->
            <section class="content">
                <!-- Publicaciones ancladas -->
                <section class="pinned-posts">
                    <h2>Publicaciones Ancladas</h2>
                    <div class="post-container">
                        <% if (anclados != null && !anclados.isEmpty()) {
                                for (PostDTO post : anclados) {%>
                        <div class="post">
                            <h3><%= post.getTitulo()%></h3>
                            <p><%= post.getContenido()%></p>
                            <% if (usuario.getTipoUsuario().toString().equalsIgnoreCase("Admor")) {%>
                            <div class="post-actions">
                                <button class="delete-btn" onclick="eliminarPost(<%= post.getId()%>)"><i class="fa-solid fa-trash"></i> Eliminar</button>
                            </div>
                            <% } %>
                        </div>
                        <% }
                        } else { %>
                        <p>No hay publicaciones ancladas.</p>
                        <% } %>
                    </div>
                </section>

                <!-- Publicaciones recientes -->
                <section class="published-posts">
                    <h2>Publicaciones Recientes</h2>
                    <div class="post-container">
                        <% if (publicaciones != null && !publicaciones.isEmpty()) {
                                boolean hayPublicaciones = false;
                                for (PostDTO post : publicaciones) {
                                    if (!post.isAnclado()) {
                                        hayPublicaciones = true;
                        %>
                        <div class="post">
                            <h3><%= post.getTitulo()%></h3>
                            <p><%= post.getContenido()%></p>
                            <div class="post-actions">
                                <% // Verificar si el usuario es administrador
                                    if (usuario.getTipoUsuario().toString().equalsIgnoreCase("Admor")) {%>
                                <button class="delete-btn" onclick="eliminarPost(<%= post.getId()%>)"><i class="fa-solid fa-trash"></i> Eliminar</button>
                                <% // Verificar si el usuario es normal y es el propietario de la publicación
                                } else if (usuario.getTipoUsuario().toString().equalsIgnoreCase("Normal") && usuario.getId().equals(post.getUsuario().getId())) {%>
                                <button class="edit-btn" onclick="editarPost(<%= post.getId()%>)"><i class="fa-solid fa-pencil"></i> Editar</button>
                                <% } %>
                            </div>

                            <!-- Comentarios -->
                            <div class="comments">
                                <h4>Comentarios:</h4>
                                <% if (post.getComentarios() != null && !post.getComentarios().isEmpty()) { %>
                                <% for (ComentarioDTO comentario : post.getComentarios()) {%>
                                <div class="comment">
                                    <p><%= comentario.getContenido()%></p>
                                    <span class="comment-time"><%= comentario.getFechaHora()%></span>
                                    <% if (usuario.getTipoUsuario().toString().equalsIgnoreCase("Admor")) {%>
                                    <button class="delete-btn" onclick="eliminarComentario(<%= comentario.getId()%>)">
                                        <i class="fa-solid fa-trash"></i> Eliminar
                                    </button>
                                    <% } %>
                                </div>
                                <% } %>
                                <% } else { %>
                                <p>No hay comentarios en esta publicación.</p>
                                <% } %>
                            </div>

                                <!-- Área para agregar comentarios (Solo para usuarios normales) -->
                                <% if (usuario.getTipoUsuario().toString().equalsIgnoreCase("Normal")) {%>
                                <div class="add-comment-section">
                                    <textarea class="comment-textarea" data-post-id="<%= post.getId()%>" placeholder="Escribe tu comentario aquí..." required></textarea>
                                    <button class="add-comment-btn">
                                        <i class="fa-solid fa-paper-plane"></i> Agregar comentario
                                    </button>
                                </div>
                                <% } %>



                        </div>
                        <% }
                            }
                            if (!hayPublicaciones) { %>
                        <p>No hay publicaciones recientes.</p>
                        <% }
                        } else { %>
                        <p>No hay publicaciones recientes.</p>
                        <% }%>
                    </div>
                </section>
            </section>
        </main>
        <script src="scripts/publicaciones.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </body>
</html>
