const submitButton = document.querySelector(".submit-btn");
submitButton.addEventListener("click", async (event) => {
    event.preventDefault();

    const postId = document.querySelector("input[name='postId']").value;
    const titulo = document.querySelector("input[name='titulo']").value;
    const contenido = document.querySelector("textarea[name='contenido']").value;

    try {
        const response = await fetch("editPostServlet", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                postId,
                titulo,
                contenido,
            }),
        });

        if (!response.headers.get("content-type")?.includes("application/json")) {
            throw new Error("La respuesta no es JSON válida.");
        }

        const result = await response.json();

        if (response.ok && result.success) {
            Swal.fire({
                title: "Éxito",
                text: "La publicación ha sido actualizada.",
                icon: "success",
            }).then(() => {
                window.location.href = "PublicacionesServlet";
            });
        } else {
            Swal.fire({
                title: "Error",
                text: result.error || "Ocurrió un error al actualizar la publicación.",
                icon: "error",
            });
        }
    } catch (error) {
        Swal.fire({
            title: "Error",
            text: "Ocurrió un error inesperado. Por favor, inténtalo más tarde.",
            icon: "error",
        });
        console.error(error);
    }
});
