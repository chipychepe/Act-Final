
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

@WebServlet(name = "SvJugadorCrear", urlPatterns = { "/SvJugadorCrear" })
public class SvJugadorCrear extends HttpServlet {

    private ControladoraLogica controlLogica = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String nacionalidad = request.getParameter("nacionalidad");
            String posicion = request.getParameter("posicion");

            int edad = Integer.parseInt(request.getParameter("edad"));
            int dorsal = Integer.parseInt(request.getParameter("dorsal"));

            String fechaTexto = request.getParameter("fechaNacimiento");
            Date fechaNacimiento = Date.valueOf(fechaTexto);

            Jugador jugador = new Jugador(
                    0,
                    nombre,
                    apellido,
                    nacionalidad,
                    posicion,
                    edad,
                    dorsal,
                    fechaNacimiento
            );

            controlLogica.crearJugador(jugador);

            response.sendRedirect(
                    request.getContextPath() + "/SvJugadorListar"
            );
            return;

        } catch (NumberFormatException e) {
            String mensaje = URLEncoder.encode(
                    "La edad y el dorsal deben ser números, y la fecha debe ser válida.",
                    StandardCharsets.UTF_8
            );

            response.sendRedirect(
                    request.getContextPath() + "/SvJugadorListar?error=" + mensaje
            );
            return;

        } catch (Exception e) {
            String mensaje = URLEncoder.encode(
                    e.getMessage() != null
                            ? e.getMessage()
                            : "Error al crear el jugador.",
                    StandardCharsets.UTF_8
            );

            response.sendRedirect(
                    request.getContextPath() + "/SvJugadorListar?error=" + mensaje
            );
            return;
        }
    }
}
