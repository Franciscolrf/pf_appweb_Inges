document.querySelector(".btn-save").addEventListener("click", function(event) {
    if (!confirm("¿Estás seguro de que deseas guardar los cambios?")) {
        event.preventDefault();
    }
});

        function confirmDelete() {
            if (confirm("¿Estás seguro de que deseas eliminar tu cuenta? Esta acción no se puede deshacer.")) {
                window.location.href = "deleteUserServlet"; 
            }
        }