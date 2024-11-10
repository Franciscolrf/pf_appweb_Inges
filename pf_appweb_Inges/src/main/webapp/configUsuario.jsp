<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dtos.UsuarioDTO" %>
<%
    UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String status = request.getParameter("status");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/configUsuarioStyle.css">
    <script src="scripts/configUsuario.js"></script>
    <title>Configuración de Usuario</title>
</head>
<body>
    <header class="header">
        <div class="user-info">
            <img src="<%= usuario.getAvatar() != null ? usuario.getAvatar() : "uploads/default.png" %>" alt="Avatar del usuario" class="avatar">
            <h2><%= usuario.getNombreCompleto() %></h2>
        </div>
    </header>
    <main class="container">
        <section class="user-details">
            <h3>Información Personal</h3>
            <% if ("success".equals(status)) { %>
                <div class="status-message">Cambios guardados exitosamente.</div>
            <% } else if ("error".equals(status)) { %>
                <div class="status-message error">Hubo un error al guardar los cambios. Inténtalo de nuevo.</div>
            <% } %>
            <form action="updateUsuarioServlet" method="post">
                <label for="nombreCompleto">Nombre Completo:</label>
                <input type="text" id="nombreCompleto" name="nombreCompleto" value="<%= usuario.getNombreCompleto() %>" required>

                <label for="correo">Correo Electrónico:</label>
                <input type="email" id="correo" name="correo" value="<%= usuario.getCorreo() %>" required>

                <label for="telefono">Teléfono:</label>
                <input type="tel" id="telefono" name="telefono" value="<%= usuario.getTelefono() %>" pattern="[0-9]{10}" required>

                <label for="direccion">Dirección:</label>
                <input type="text" id="direccion" name="direccion" value="<%= usuario.getDireccion() %>" required>

                <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%= usuario.getFechaNacimiento() %>" required>

                <label for="genero">Género:</label>
                <select id="genero" name="genero" required>
                    <option value="masculino" <%= usuario.getGenero().equals("masculino") ? "selected" : "" %>>Masculino</option>
                    <option value="femenino" <%= usuario.getGenero().equals("femenino") ? "selected" : "" %>>Femenino</option>
                </select>

                <button type="submit" class="btn-save">Guardar Cambios</button>
            </form>
        </section>
    </main>
</body>
</html>
