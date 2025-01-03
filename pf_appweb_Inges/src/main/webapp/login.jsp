<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/styleLogin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
    <title>Login</title>
</head>

<body>
    <main class="container">
        <section class="form-container">
            <div class="icon-user">
                <i class="fa fa-user" aria-hidden="true"></i>
            </div>

            <h1>Inicio de Sesión</h1>

            <!-- Formulario sin atributo action -->
            <form id="loginForm" class="login-form">
                <label for="correo">Correo Electrónico:</label>
                <input type="email" id="correo" name="correo" required pattern="^[a-zA-Z0-9._%+-]+@.+$"
                    title="El correo debe contener un @">

                <label for="contrasenia">Contraseña:</label>
                <input type="password" id="contrasenia" name="contrasenia" required minlength="8"
                    title="Debe tener al menos 8 caracteres">

                <button type="submit" class="btn">
                    <i class="fa-solid fa-right-to-bracket" aria-hidden="true"></i>
                </button>
            </form>

            <!-- Contenedor para mensajes dinámicos -->
            <div id="error-message" class="error-message" style="color: red; text-align: center; display: none;"></div>

            <div class="register-prompt">
                <p>¿No tienes cuenta? <a href="registro.jsp" class="register-link">Regístrate!!!</a></p>
            </div>

            <a href="index.html">
                <button class="exit-btn"><i class="fa-solid fa-xmark"></i></button>
            </a>
        </section>
    </main>

    <script src="scripts/login.js"></script>
</body>

</html>
