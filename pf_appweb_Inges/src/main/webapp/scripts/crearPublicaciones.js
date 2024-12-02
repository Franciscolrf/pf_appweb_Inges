document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("createPostForm");
    const submitButton = document.getElementById("submitPostBtn");

    submitButton.addEventListener("click", async () => {
        const formData = new FormData(form);

        try {
            const response = await fetch("createPostServlet", {
                method: "POST",
                body: formData,
            });

            const result = await response.json();

            if (response.ok && result.success) {
                alert("Publicación creada con éxito");
                window.location.href = "PublicacionesServlet";
            } else {
                alert(`Error: ${result.message || "No se pudo crear la publicación."}`);
            }
        } catch (error) {
            console.error("Error al crear la publicación:", error);
            alert("Ocurrió un error inesperado.");
        }
    });
});
