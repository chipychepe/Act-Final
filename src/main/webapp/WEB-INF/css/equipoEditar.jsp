
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.app.logica.Equipo" %>

<%
    Equipo equipo = (Equipo) request.getAttribute("equipo");

    if (equipo == null) {
        response.sendRedirect(request.getContextPath() + "/SvEquipoListar");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Editar equipo - Proyecto Fútbol</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/stylo/estilo.css">
</head>

<body>

<header>
    <h1>⚽ Proyecto Fútbol</h1>

   
<nav>
    <a href="${pageContext.request.contextPath}/index.html">Inicio</a>
    <a href="${pageContext.request.contextPath}/SvJugadorListar">Jugadores</a>
    <a href="${pageContext.request.contextPath}/SvEquipoListar">Equipos</a>
</nav>

</header>

<main>
    <h2>Editar equipo</h2>

    <p>Modificá los datos del equipo y guardá los cambios.</p>

    <form class="formulario"
          action="${pageContext.request.contextPath}/SvEquipoEditar"
          method="post">

        <input type="hidden" name="id" value="<%= equipo.getId() %>">

        <label for="nombre">Nombre del equipo</label>
        <input type="text"
               id="nombre"
               name="nombre"
               value="<%= equipo.getNombre() %>"
               required>

        <label for="ciudad">Ciudad</label>
        <input type="text"
               id="ciudad"
               name="ciudad"
               value="<%= equipo.getCiudad() %>"
               required>

        <label for="estadio">Estadio</label>
        <input type="text"
               id="estadio"
               name="estadio"
               value="<%= equipo.getEstadio() %>"
               required>

        <label for="titulos">Cantidad de títulos</label>
        <input type="number"
               id="titulos"
               name="titulos"
               min="0"
               value="<%= equipo.getTitulos() %>"
               required>

        <div class="acciones">
            <button type="submit" class="boton">
                Guardar cambios
            </button>

            <a class="boton secundario"
               href="${pageContext.request.contextPath}/SvEquipoListar">
                Cancelar
            </a>
        </div>

    </form>
</main>

<footer>
    Benjamin Berrios y Jefferson Mallon — Instituto Técnico Ntra. Sra. de Fátima (A-893)
</footer>

</body>
</html>
