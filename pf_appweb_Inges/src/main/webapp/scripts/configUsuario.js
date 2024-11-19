document.querySelector(".btn-save").addEventListener("click", function(event) {
    if (!confirm("¿Estás seguro de que deseas guardar los cambios?")) {
        event.preventDefault();
    }
});
