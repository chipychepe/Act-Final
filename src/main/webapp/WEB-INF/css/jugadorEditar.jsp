```jsp
<%@page import="com.app.logica.Jugador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Jugador jugador = (Jugador) request.getAttribute("jugador");

    if (jugador == null) {
        response.sendRedirect(request.getContextPath() + "/SvJugadorListar");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Editar jugador - Proyecto Fútbol</title>
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
    <h2>Editar jugador</h2>
    <p>Modificá los datos del jugador y guardá los cambios.</p>

    <form action="${pageContext.request.contextPath}/SvJugadorEditar"
          method="post" class="formulario">

        <input type="hidden" name="id" value="<%= jugador.getId() %>">

        <label for="nombre">Nombre</label>
        <input type="text" id="nombre" name="nombre"
               value="<%= jugador.getNombre() %>" required>

        <label for="apellido">Apellido</label>
        <input type="text" id="apellido" name="apellido"
               value="<%= jugador.getApellido() %>" required>

        <label for="nacionalidad">Nacionalidad</label>
        <input type="text" id="nacionalidad" name="nacionalidad"
               value="<%= jugador.getNacionalidad() %>" required>

        <label for="posicion">Posición</label>
        <input type="text" id="posicion" name="posicion"
               value="<%= jugador.getPosicion() %>" required>

        <label for="edad">Edad</label>
        <input type="number" id="edad" name="edad" min="1"
               value="<%= jugador.getEdad() %>" required>

        <label for="dorsal">Dorsal</label>
        <input type="number" id="dorsal" name="dorsal" min="0"
               value="<%= jugador.getDorsal() %>" required>

        <label for="fechaNacimiento">Fecha de nacimiento</label>
        <input type="date" id="fechaNacimiento" name="fechaNacimiento"
               value="<%= jugador.getFechaNacimiento() != null
                       ? jugador.getFechaNacimiento().toString() : "" %>"
               required>

        <button type="submit">Guardar cambios</button>

        <a class="boton secundario"
           href="${pageContext.request.contextPath}/SvJugadorListar">
            Cancelar
        </a>
    </form>
</main>

<footer>
    Benjamin Berrios y Jefferson Mallon — Instituto Técnico Ntra. Sra. de Fátima (A-893)
</footer>

</body>
</html>
```