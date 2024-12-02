document.addEventListener("DOMContentLoaded", function () {
    // Botón de guardar cambios
    const saveButton = document.querySelector(".save-btn");
    saveButton.addEventListener("click", function (event) {
        event.preventDefault(); // Prevenir el envío predeterminado del formulario

        Swal.fire({
            title: "¿Estás seguro de que deseas guardar los cambios?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Sí, guardar!",
            cancelButtonText: "Cancelar",
        }).then((result) => {
            if (result.isConfirmed) {
                saveButton.closest("form").submit(); // Envía el formulario si se confirma la acción
            }
        });
    });

    // Botón de eliminar cuenta
    const deleteButton = document.querySelector("#btn-delete-user");
    deleteButton.addEventListener("click", async function () {
        Swal.fire({
            title: "¿Estás seguro de que deseas eliminar tu cuenta?",
            text: "Esta acción no se puede deshacer.",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#d33",
            cancelButtonColor: "#3085d6",
            confirmButtonText: "Sí, eliminar!",
            cancelButtonText: "Cancelar",
        }).then(async (result) => {
            if (result.isConfirmed) {
                try {
                    const response = await fetch("deleteUserServlet", {
                        method: "DELETE",
                        headers: {
                            "Content-Type": "application/json",
                        },
                    });

                    if (response.ok) {
                        Swal.fire({
                            title: "Eliminada!",
                            text: "Tu cuenta ha sido eliminada.",
                            icon: "success",
                        }).then(() => {
                            window.location.href = "login.jsp"; // Redirigir a la página de inicio de sesión
                        });
                    } else {
                        Swal.fire({
                            title: "Error",
                            text: "No se pudo eliminar la cuenta.",
                            icon: "error",
                        });
                    }
                } catch (error) {
                    console.error("Error al intentar eliminar la cuenta:", error);
                    Swal.fire({
                        title: "Error",
                        text: "Ocurrió un error inesperado. Intenta nuevamente.",
                        icon: "error",
                    });
                }
            }
        });
    });
});
