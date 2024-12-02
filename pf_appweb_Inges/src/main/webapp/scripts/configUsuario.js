document.addEventListener("DOMContentLoaded", function () {
    // Botón de guardar cambios
    const saveButton = document.querySelector(".save-btn");
    saveButton.addEventListener("click", function (event) {
        if (!confirm("¿Estás seguro de que deseas guardar los cambios?")) {
            event.preventDefault(); // Cancela el evento si el usuario no confirma
        }
    });

    // Botón de eliminar cuenta
    const deleteButton = document.querySelector("#btn-delete-user");
    deleteButton.addEventListener("click", async function () {
        const confirmDelete = confirm("¿Estás seguro de que deseas eliminar tu cuenta? Esta acción no se puede deshacer.");
        if (confirmDelete) {
            try {
                const response = await fetch("deleteUserServlet", {
                    method: "DELETE", // Cambiamos a DELETE para semántica REST
                    headers: {
                        "Content-Type": "application/json",
                    },
                });

                if (response.ok) {
                    alert("Tu cuenta ha sido eliminada.");
                    window.location.href = "login.jsp"; // Redirigir a la página de inicio de sesión
                } else {
                    const errorData = await response.json();
                    alert(`Error: ${errorData.error || "No se pudo eliminar la cuenta."}`);
                }
            } catch (error) {
                console.error("Error al intentar eliminar la cuenta:", error);
                alert("Ocurrió un error inesperado. Intenta nuevamente.");
            }
        }
    });
});
