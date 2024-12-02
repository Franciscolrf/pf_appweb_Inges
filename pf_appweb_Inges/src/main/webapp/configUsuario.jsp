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
    <link rel="stylesheet" href="styles/styleConfigUsuario.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
    <title>Configuración de Usuario</title>
</head>
<body>
    <header>
    <a href="PublicacionesServlet" class="close-btn"><i class="fa-solid fa-xmark"></i></a>
    <h1>Configuración de Usuario</h1>
</header>


    <main class="container">
        <section class="user-config">
            <div class="avatar-container">
                <img src="<%= usuario.getAvatar() != null ? usuario.getAvatar() : "uploads/default.png" %>" alt="Avatar del usuario" class="avatar">
                <p><%= usuario.getNombreCompleto() %></p>
            </div>
                <c:if test="${not empty mensaje}">
                    <div class="${tipoMensaje == 'success' ? 'alert-success' : 'alert-error'}">
                        ${mensaje}
                    </div>
                </c:if>


            <form action="updateUsuarioServlet" method="post" enctype="multipart/form-data">
                <label for="nombreCompleto">Nombre Completo:</label>
                <input type="text" id="nombreCompleto" name="nombreCompleto" value="<%= usuario.getNombreCompleto() %>" pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+" required>

                <label for="correo">Correo Electrónico:</label>
                <input type="email" id="correo" name="correo" value="<%= usuario.getCorreo() %>" required>

                <label for="telefono">Teléfono:</label>
                <input type="text" id="telefono" name="telefono" value="<%= usuario.getTelefono() %>" required>

                <label for="direccion">Dirección:</label>
                <input type="text" id="direccion" name="direccion" value="<%= usuario.getDireccion()%>" required maxlength="10">


                <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%= usuario.getFechaNacimiento() %>" required>

                <label for="genero">Género:</label>
                <select id="genero" name="genero" required>
                    <option value="Masculino" <%= usuario.getGenero().equals("Masculino") ? "selected" : "" %>>Masculino</option>
                    <option value="Femenino" <%= usuario.getGenero().equals("Femenino") ? "selected" : "" %>>Femenino</option>
                </select>

                <label for="avatar">Actualizar Avatar:</label>
                <label for="avatar" class="file-label">
                    <i class="fa fa-upload" aria-hidden="true"></i>
                </label>
                <input type="file" id="avatar" name="avatar" accept="image/png, image/jpeg">

                <label for="contrasenia">Nueva Contraseña:</label>
                <input type="password" id="contrasenia" name="contrasenia"  minlength="8" title="Debe tener al menos 8 caracteres">

                <div class="button-group">
                    <button type="submit" class="save-btn">Guardar Cambios</button>
                    <button type="button" class="delete-btn" onclick="confirmDelete()">Eliminar Cuenta</button>
                </div>
            </form>
        </section>
    </main>

                <script src="scripts/configUsuario.js"></script>
</body>
</html>

