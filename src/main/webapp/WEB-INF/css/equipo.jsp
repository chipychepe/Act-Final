
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List, com.app.logica.Equipo" %>

<%
    List<Equipo> equipos = (List<Equipo>) request.getAttribute("equipos");
    String error = request.getParameter("error");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Equipos</title>
    <link rel="stylesheet" href="stylo/estilo.css">
</head>

<body>

<header>
    <h1>Proyecto Fútbol</h1>
    
<nav>
    <a href="${pageContext.request.contextPath}/index.html">Inicio</a>
    <a href="${pageContext.request.contextPath}/SvJugadorListar">Jugadores</a>
    <a href="${pageContext.request.contextPath}/SvEquipoListar">Equipos</a>
</nav>

</header>

<main>

    <% if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <h2>Equipos</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Ciudad</th>
            <th>Estadio</th>
            <th>Títulos</th>
            <th>Acciones</th>
        </tr>

        <% if (equipos == null || equipos.isEmpty()) { %>
            <tr>
                <td colspan="6" class="vacio">Todavía no hay equipos cargados.</td>
            </tr>
        <% } else {
            for (Equipo e : equipos) { %>
                <tr>
                    <td><%= e.getId() %></td>
                    <td><%= e.getNombre() %></td>
                    <td><%= e.getCiudad() %></td>
                    <td><%= e.getEstadio() %></td>
                    <td><%= e.getTitulos() %></td>
                    <td class="acciones">
                        <a class="boton" href="SvEquipoEditar?id=<%= e.getId() %>">Editar</a>

                        <form action="SvEquipoEliminar" method="post"
                              onsubmit="return confirm('¿Eliminar este equipo?');">
                            <input type="hidden" name="id" value="<%= e.getId() %>">
                            <button type="submit" class="peligro">Eliminar</button>
                        </form>
                    </td>
                </tr>
        <%  }
           } %>
    </table>

    <h2>Nuevo equipo</h2>

    <form class="formulario" action="SvEquipoCrear" method="post">
        <label>Nombre
            <input type="text" name="nombre" required>
        </label>

        <label>Ciudad
            <input type="text" name="ciudad" required>
        </label>

        <label>Estadio
            <input type="text" name="estadio" required>
        </label>

        <label>Títulos
            <input type="number" name="titulos" min="0" required>
        </label>

        <button type="submit">Guardar</button>
    </form>

</main>

<footer>
    Benjamin Berrios y Jefferson Mallon — Instituto Técnico Ntra. Sra. de Fátima (A-893)
</footer>

</body>
</html>
