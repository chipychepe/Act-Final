
<%@page import="java.util.List"%>
<%@page import="com.app.logica.Jugador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Jugador> jugadores =
        (List<Jugador>) request.getAttribute("jugadores");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Jugadores - Proyecto Fútbol</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/stylo/estilo.css">
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
    <h2>Jugadores</h2>

    <%
        String error = request.getParameter("error");
        if (error != null && !error.isEmpty()) {
    %>
        <p class="error"><%= java.net.URLDecoder.decode(error, "UTF-8") %></p>
    <% } %>

    <h2>Buscar jugadores por posición</h2>

    <form action="${pageContext.request.contextPath}/SvJugadorListar"
          method="get" class="formulario">

        <label for="posicion">Posición</label>
        <input type="text" id="posicion" name="posicion"
               placeholder="Ej.: Delantero"
               value="<%= request.getAttribute("posicionBuscada") != null
                       ? request.getAttribute("posicionBuscada") : "" %>">

        <button type="submit">Buscar</button>

        <a class="boton secundario"
           href="${pageContext.request.contextPath}/SvJugadorListar">
            Mostrar todos
        </a>
    </form>

    <% if (jugadores == null || jugadores.isEmpty()) { %>
        <p class="vacio">No hay jugadores registrados.</p>
    <% } else { %>

        <div class="tabla-contenedor">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Nacionalidad</th>
                        <th>Posición</th>
                        <th>Edad</th>
                        <th>Dorsal</th>
                        <th>Fecha de nacimiento</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Jugador j : jugadores) { %>
                        <tr>
                            <td><%= j.getId() %></td>
                            <td><%= j.getNombre() %></td>
                            <td><%= j.getApellido() %></td>
                            <td><%= j.getNacionalidad() %></td>
                            <td><%= j.getPosicion() %></td>
                            <td><%= j.getEdad() %></td>
                            <td><%= j.getDorsal() %></td>
                            <td><%= j.getFechaNacimiento() %></td>
                            <td class="acciones">
                                <a class="boton"
                                   href="${pageContext.request.contextPath}/SvJugadorEditar?id=<%= j.getId() %>">
                                    Editar
                                </a>

                                <form action="${pageContext.request.contextPath}/SvJugadorEliminar"
                                      method="post"
                                      onsubmit="return confirm('¿Querés eliminar este jugador?');">
                                    <input type="hidden" name="id"
                                           value="<%= j.getId() %>">
                                    <button type="submit" class="peligro">
                                        Eliminar
                                    </button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    <% } %>

    <h2>Nuevo jugador</h2>

    <form action="${pageContext.request.contextPath}/SvJugadorCrear"
          method="post" class="formulario">

        <label for="nombre">Nombre</label>
        <input type="text" id="nombre" name="nombre" required>

        <label for="apellido">Apellido</label>
        <input type="text" id="apellido" name="apellido" required>

        <label for="nacionalidad">Nacionalidad</label>
        <input type="text" id="nacionalidad" name="nacionalidad" required>

        <label for="posicionNueva">Posición</label>
        <input type="text" id="posicionNueva" name="posicion" required>

        <label for="edad">Edad</label>
        <input type="number" id="edad" name="edad" min="1" required>

        <label for="dorsal">Dorsal</label>
        <input type="number" id="dorsal" name="dorsal" min="0" required>

        <label for="fechaNacimiento">Fecha de nacimiento</label>
        <input type="date" id="fechaNacimiento"
               name="fechaNacimiento" required>

        <button type="submit">Guardar jugador</button>
    </form>
</main>

<footer>
    Benjamin Berrios y Jefferson Mallon — Instituto Técnico Ntra. Sra. de Fátima (A-893)
</footer>

</body>
</html>
