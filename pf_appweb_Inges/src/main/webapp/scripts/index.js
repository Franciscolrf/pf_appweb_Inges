document.addEventListener("DOMContentLoaded", () => {
    // Manejar evento del botón "Iniciar Sesión"
    document.querySelector("#loginButton").addEventListener("click", (event) => {
        event.preventDefault(); // Evitar comportamiento por defecto
        window.location.href = "login.jsp"; // Redirigir a la página de inicio de sesión
    });

    // Manejar evento del botón "Crear Cuenta"
    document.querySelector("#registerButton").addEventListener("click", (event) => {
        event.preventDefault(); // Evitar comportamiento por defecto
        window.location.href = "registro.jsp"; // Redirigir a la página de registro
    });
});
