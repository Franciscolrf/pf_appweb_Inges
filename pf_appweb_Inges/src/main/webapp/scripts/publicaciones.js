// Función para eliminar un post
const eliminarPost = async (postId) => {
    Swal.fire({
        title: "¿Estás seguro?",
        text: "No podrás deshacer esta acción.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, eliminar!"
    }).then(async (result) => {
        if (result.isConfirmed) {
            try {
                const response = await fetch(`deletePostServlet?postId=${encodeURIComponent(postId)}`, {
                    method: "GET"
                });

                if (response.ok) {
                    Swal.fire("Eliminado!", "El post ha sido eliminado.", "success").then(() => {
                        location.reload();
                    });
                } else {
                    Swal.fire({
                        title: "Error",
                        text: "No se pudo eliminar el post. Intenta nuevamente.",
                        icon: "error"
                    });
                }
            } catch (error) {
                Swal.fire({
                    title: "Error",
                    text: "Ocurrió un problema al eliminar el post.",
                    icon: "error"
                });
                console.error(error);
            }
        }
    });
};

// Función para eliminar un comentario
const eliminarComentario = async (comentarioId) => {
    Swal.fire({
        title: "¿Estás seguro?",
        text: "No podrás deshacer esta acción.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, eliminar!"
    }).then(async (result) => {
        if (result.isConfirmed) {
            try {
                const response = await fetch(`deleteCommentServlet?comentarioId=${encodeURIComponent(comentarioId)}`, {
                    method: "GET"
                });

                if (response.ok) {
                    Swal.fire("Eliminado!", "El comentario ha sido eliminado.", "success").then(() => {
                        location.reload();
                    });
                } else {
                    Swal.fire({
                        title: "Error",
                        text: "No se pudo eliminar el comentario. Intenta nuevamente.",
                        icon: "error"
                    });
                }
            } catch (error) {
                Swal.fire({
                    title: "Error",
                    text: "Ocurrió un problema al eliminar el comentario.",
                    icon: "error"
                });
                console.error(error);
            }
        }
    });
};

// Función para editar un post
const editarPost = (postId) => {
    Swal.fire({
        title: "Editar post",
        text: "¿Deseas editar el post?",
        icon: "question",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, editar!"
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = `editPostServlet?postId=${encodeURIComponent(postId)}`;
        }
    });
};

document.addEventListener("DOMContentLoaded", () => {
    const addCommentButtons = document.querySelectorAll(".add-comment-btn");

    addCommentButtons.forEach((button) => {
        button.addEventListener("click", async (event) => {
            event.preventDefault();
            const postId = button.previousElementSibling.getAttribute("data-post-id");
            const contenido = button.previousElementSibling.value.trim();

            if (contenido === "") {
                Swal.fire({
                    icon: 'warning',
                    title: 'Advertencia',
                    text: 'El contenido del comentario no puede estar vacío.',
                });
                return;
            }

            try {
                const response = await fetch('/pf_appweb_Inges/comentarioServlet', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        postId: postId,
                        contenido: contenido
                    })
                });


                if (response.ok) {
                    const responseData = await response.json();
                    if (responseData.success) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Comentario agregado',
                            text: responseData.message,
                        }).then(() => {
                            location.reload();
                        });
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: responseData.message,
                        });
                    }
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'No se pudo agregar el comentario. Intenta nuevamente.',
                    });
                }
            } catch (error) {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Ocurrió un problema al agregar el comentario.',
                });
                console.error('Error:', error);
            }
        });
    });
});

