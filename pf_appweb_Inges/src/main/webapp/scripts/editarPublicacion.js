function editarPost(postId) {
        Swal.fire({
        title: "Deseas editar el post?",
        text: "Deseas guardar los cambios?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Si, borrar!"
    }).then((result) => {
        if (result.isConfirmed) {

            window.location.href = "editPostServlet?postId=" + encodeURIComponent(postId);

        }
    });
}