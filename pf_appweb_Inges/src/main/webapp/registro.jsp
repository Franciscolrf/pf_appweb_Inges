<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/styleRegistro.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
    <title>Registro Usuario</title>
</head>

<body>
    <main class="container">
        <section class="form-container">
            <!-- Enlace para redirigir al login -->
            <a href="login.jsp" class="login-btn">¿Ya tienes cuenta? ¡Loguéate!</a>
            <div class="icon-user">
                <i class="fa fa-user" aria-hidden="true"></i>
            </div>

            <h1>Registro de Usuario</h1>

            
            <!-- Mostrar mensaje dinámico -->
            <c:if test="${not empty mensaje}">
                <div class="${tipoMensaje == 'success' ? 'alert-success' : 'alert-error'}">
                    ${mensaje}
                </div>
            </c:if>

            <form action="uploadServlet" method="post" class="register-form" enctype="multipart/form-data">
                <label for="nombreCompleto">Nombre completo:</label>
                <input type="text" id="nombreCompleto" name="nombreCompleto" required 
                       pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+" title="Solo se permiten letras">

                <label for="correo">Correo Electrónico:</label>
                <input type="email" id="correo" name="correo" required 
                       pattern="^[a-zA-Z0-9._%+-]+@.+$" title="El correo debe contener un @">

                <label for="contrasenia">Contraseña:</label>
                <input type="password" id="contrasenia" name="contrasenia" required minlength="8" 
                       title="Debe tener al menos 8 caracteres">

                <label for="confirmarContrasenia">Confirmar Contraseña:</label>
                <input type="password" id="confirmarContrasenia" name="confirmarContrasenia" required>

                <label for="telefono">Teléfono:</label>
                <input type="tel" id="telefono" name="telefono" required  maxlength="10"
                       pattern="[0-9]{10}" title="Solo se permiten números">

                <label>Avatar (Opcional):</label>
                <label for="avatar" class="file-label">
                    <div class="file-icon">
                        <i id="uploadIcon" class="fa fa-upload" aria-hidden="true"></i>
                    </div>
                </label>
                <input type="file" id="avatar" name="avatar" accept="image/png, image/jpeg" onchange="changeIconColor()">

                <label for="direccion">Dirección:</label>
                <input type="text" id="direccion" name="direccion" required title="Ingrese su dirección">

                <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>

                <label for="genero">Género:</label>
                <select id="genero" name="genero" required>
                    <option value="masculino">Masculino</option>
                    <option value="femenino">Femenino</option>
                </select>

                <button type="submit" class="btn">
                    <i class="fa fa-check" aria-hidden="true"></i>
                </button>
            </form>


            <a href="index.jsp">
                <button class="exit-btn">
                    <i class="fa-solid fa-xmark"></i>
                </button>
            </a>
        </section>
    </main>

                <script src="scripts/registro.js"></script>
</body>

</html>
