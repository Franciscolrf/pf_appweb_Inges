<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error Interno del Servidor</title>
    <link rel="stylesheet" href="styles/errorStyles.css">
</head>
<body>
    <div class="error-container">
        <h1>Error 500: Excepción Interna</h1>
        <p>Algo salió mal en el servidor. Estamos trabajando para solucionarlo.</p>
        <p><strong>Detalles del error:</strong></p>
        <<pre>
            <%= request.getAttribute("jakarta.servlet.error.exception") != null
            ? request.getAttribute("jakarta.servlet.error.exception").toString()
            : "No hay detalles disponibles."%>
        </pre>

        <a href="index.html" class="btn">Volver al inicio</a>
    </div>
</body>
</html>
