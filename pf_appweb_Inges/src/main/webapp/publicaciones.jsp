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
                <c:if test="${usuario.tipoUsuario == 'Admor'}">
                    <span class="admin-tag">Administrador</span>
                </c:if>
            </div>
            <ul class="sidebar-links">
                <li><a href="configUsuario.jsp"><i class="fa-solid fa-gear"></i> Configuración</a></li>
                <c:if test="${usuario.tipoUsuario == 'Admor'}">
                    <li><a href="crearPublicacionAnclada.jsp" class="create-btn"><i class="fa-solid fa-thumbtack"></i> Crear publicación anclada</a></li>
                </c:if>
                <li><a href="crearPublicaciones.jsp" class="create-btn"><i class="fa-solid fa-plus"></i> Crear publicación</a></li>
                <li><a href="logoutServlet"><i class="fa-solid fa-sign-out-alt"></i> Cerrar Sesión</a></li>
            </ul>
        </aside>
        <section class="content">
            <!-- Publicaciones Ancladas -->
            <section class="pinned-posts">
                <h2>Publicaciones Ancladas</h2>
                <div class="post-container">
                    <c:forEach var="post" items="${anclados}">
                        <div class="post">
                            <h3>${post.titulo}</h3>
                            <p>${post.contenido}</p>
                            <c:if test="${usuario.tipoUsuario == 'Admor'}">
                                <div class="post-actions">
                                    <button class="delete-btn" onclick="eliminarPost(${post.id})"><i class="fa-solid fa-trash"></i> Eliminar</button>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </section>

            <!-- Publicaciones Recientes -->
            <section class="published-posts">
                <h2>Publicaciones Recientes</h2>
                <div class="post-container">
                    <c:forEach var="post" items="${posts}">
                        <div class="post">
                            <h3>${post.titulo}</h3>
                            <p>${post.contenido}</p>
                            <c:if test="${usuario.tipoUsuario == 'Admor'}">
                                <div class="post-actions">
                                    <button class="delete-btn" onclick="eliminarPost(${post.id})"><i class="fa-solid fa-trash"></i> Eliminar</button>
                                </div>
                            </c:if>
                            <c:if test="${usuario.tipoUsuario == 'Normal' && post.usuario.id == usuario.id}">
                                <div class="post-actions">
                                    <button class="edit-btn" onclick="editarPost(${post.id})"><i class="fa-solid fa-pencil"></i> Editar</button>
                                </div>
                            </c:if>
                            <!-- Comentarios -->
                            <div class="comments">
                                <h4>Comentarios:</h4>
                                <c:forEach var="comentario" items="${post.comentarios}">
                                    <div class="comment">
                                        <p>${comentario.contenido}</p>
                                        <span class="comment-time">${comentario.fechaHora}</span>
                                        <c:if test="${usuario.tipoUsuario == 'Admor'}">
                                            <button class="delete-comment-btn" onclick="eliminarComentario(${comentario.id})"><i class="fa-solid fa-trash"></i></button>
                                        </c:if>
                                    </div>
                                </c:forEach>
                                <c:if test="${usuario.tipoUsuario == 'Normal'}">
                                    <form action="crearComentarioServlet" method="POST" class="comment-form">
                                        <textarea name="comentario" rows="2" placeholder="Escribe un comentario..." required></textarea>
                                        <input type="hidden" name="postId" value="${post.id}">
                                        <button type="submit"><i class="fa-solid fa-paper-plane"></i> Comentar</button>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </section>
    </main>
    <script>
        function eliminarPost(id) {
            if (confirm("¿Estás seguro de que deseas eliminar esta publicación?")) {
                window.location.href = `deletePostServlet?id=${id}`;
            }
        }

        function eliminarComentario(id) {
            if (confirm("¿Estás seguro de que deseas eliminar este comentario?")) {
                window.location.href = `deleteCommentServlet?id=${id}`;
            }
        }

        function editarPost(id) {
            window.location.href = `editarPublicacion.jsp?id=${id}`;
        }
    </script>
</body>
</html>
