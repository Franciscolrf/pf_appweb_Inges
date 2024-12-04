document.addEventListener("DOMContentLoaded", () => {
    const registroForm = document.getElementById("registroForm");

    registroForm.addEventListener("submit", (event) => {
        event.preventDefault(); // Evitar el envío directo

        const formData = new FormData(registroForm);

        fetch('uploadServlet', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                Swal.fire({
                    icon: 'success',
                    title: 'Registro exitoso',
                    text: data.message
                }).then(() => {
                    window.location.href = "login.jsp";
                });
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: data.message
                });
            }
        })
        .catch(error => {
            console.error('Error:', error);
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Ocurrió un problema al registrar el usuario. Inténtalo nuevamente.'
            });
        });
    });

    // Función para cambiar el color del icono al seleccionar un avatar
    const changeIconColor = () => {
        const uploadIcon = document.getElementById("uploadIcon");
        uploadIcon.style.color = "green";
    };

    // Añadir el evento `change` al input de avatar
    const avatarInput = document.getElementById("avatar");
    avatarInput.addEventListener("change", changeIconColor);
});
