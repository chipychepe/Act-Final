package com.app.servlets;

import com.app.logica.Jugador;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;

@WebServlet(name = "SvJugadorEditar", urlPatterns = { "/SvJugadorEditar" })
public class SvJugadorEditar extends HttpServlet {

    private ControladoraLogica controlLogica = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Jugador jugador = controlLogica.buscarUnJugador(id);

            if (jugador == null) {
                response.sendRedirect(request.getContextPath()
                        + "/SvJugadorListar?error="
                        + URLEncoder.encode("El jugador no existe.",
                                StandardCharsets.UTF_8));
                return;
            }

            request.setAttribute("jugador", jugador);
            request.getRequestDispatcher("/WEB-INF/css/jugadorEditar.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            if (!response.isCommitted()) {
                String mensaje = URLEncoder.encode(
                        "No se pudo cargar el jugador. Verificá el ID.",
                        StandardCharsets.UTF_8);

                response.sendRedirect(request.getContextPath()
                        + "/SvJugadorListar?error=" + mensaje);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String nacionalidad = request.getParameter("nacionalidad");
            String posicion = request.getParameter("posicion");

            int edad = Integer.parseInt(request.getParameter("edad"));
            int dorsal = Integer.parseInt(request.getParameter("dorsal"));

            String fechaTexto = request.getParameter("fechaNacimiento");
            Date fechaNacimiento = Date.valueOf(fechaTexto);

            Jugador jugador = new Jugador(
                    id,
                    nombre,
                    apellido,
                    nacionalidad,
                    posicion,
                    edad,
                    dorsal,
                    fechaNacimiento
            );

            controlLogica.editarJugador(jugador);

            response.sendRedirect(
                    request.getContextPath() + "/SvJugadorListar");
            return;

        } catch (NumberFormatException e) {
            redirigirConError(request, response,
                    "La edad, el dorsal o el ID tienen un formato inválido.");
            return;

        } catch (IllegalArgumentException e) {
            redirigirConError(request, response,
                    "La fecha de nacimiento no es válida.");
            return;

        } catch (Exception e) {
            String mensaje = e.getMessage() != null
                    ? e.getMessage()
                    : "No se pudo editar el jugador.";

            redirigirConError(request, response, mensaje);
        }
    }

    private void redirigirConError(HttpServletRequest request,
                                   HttpServletResponse response,
                                   String mensaje) throws IOException {

        String mensajeCodificado = URLEncoder.encode(
                mensaje, StandardCharsets.UTF_8);

        response.sendRedirect(request.getContextPath()
                + "/SvJugadorListar?error=" + mensajeCodificado);
    }
}
