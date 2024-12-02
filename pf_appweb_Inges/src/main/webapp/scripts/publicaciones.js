function eliminarPost(postId) {
    Swal.fire({
        title: "Estas seguro que quieres eliminar el post??",
        text: "No se puede deshacer!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Si, borrar!"
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                title: "Eliminado!",
                text: "El post ha sido eliminado..",
                icon: "success"
            });
            window.location.href = "deletePostServlet?postId=" + encodeURIComponent(postId);
        }
    });
}

function eliminarComentario(comentarioId) {
    Swal.fire({
        title: "Estas seguro que quieres eliminar el comentario??",
        text: "No se puede deshacer!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Si, borrar!"
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                title: "Eliminado!",
                text: "El comentario ha sido eliminado..",
                icon: "success"
            });
            window.location.href = "deleteCommentServlet?comentarioId=" + encodeURIComponent(comentarioId);

        }
    });
}

function editarPost(postId) {
        Swal.fire({
        title: "Editar post",
        text: "Deseas editar el post?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Si, editar!"
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = "editPostServlet?postId=" + encodeURIComponent(postId);
        }
    });
}

