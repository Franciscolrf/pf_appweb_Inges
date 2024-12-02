document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("createPinnedPostForm");
    const submitButton = document.getElementById("submitPinnedPostBtn");

    submitButton.addEventListener("click", async () => {
        const formData = new FormData(form);
        formData.append("anclado", "true");

        try {
            const response = await fetch("createPostServlet", {
                method: "POST",
                body: formData,
            });

            const result = await response.json();

            if (response.ok && result.success) {
                alert("Publicación anclada creada con éxito");
                window.location.href = "PublicacionesServlet";
            } else {
                alert(`Error: ${result.message || "No se pudo crear la publicación anclada."}`);
            }
        } catch (error) {
            console.error("Error al crear la publicación anclada:", error);
            alert("Ocurrió un error inesperado.");
        }
    });
});
