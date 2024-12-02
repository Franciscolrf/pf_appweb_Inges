document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("loginForm");
    const errorMessage = document.getElementById("error-message");

    loginForm.addEventListener("submit", async (event) => {
        event.preventDefault();

        const correo = loginForm.correo.value;
        const contrasenia = loginForm.contrasenia.value;

        try {
            const response = await fetch("/pf_appweb_Inges/loginServlet", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ correo, contrasenia }),
            });

            const result = await response.json();

            if (response.ok && result.success) {
                // Redirigir si el inicio de sesión fue exitoso
                window.location.href = "PublicacionesServlet";
            } else {
                // Mostrar mensaje de error
                errorMessage.style.display = "block";
                errorMessage.textContent = result.error || "Error al iniciar sesión.";
            }
        } catch (error) {
            console.error("Error:", error);
            errorMessage.style.display = "block";
            errorMessage.textContent = "Ocurrió un error inesperado. Intenta de nuevo más tarde.";
        }
    });
});
