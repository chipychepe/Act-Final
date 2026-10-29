
package com.app.servlets;

import com.app.logica.Equipo;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "SvEquipoCrear", urlPatterns = { "/SvEquipoCrear" })
public class SvEquipoCrear extends HttpServlet {

    private ControladoraLogica controlLogica = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nombre = request.getParameter("nombre");
            String ciudad = request.getParameter("ciudad");
            String estadio = request.getParameter("estadio");
            int titulos = Integer.parseInt(request.getParameter("titulos"));

            Equipo equipo = new Equipo(0, nombre, ciudad, estadio, titulos);

            controlLogica.crearEquipo(equipo);

            response.sendRedirect(request.getContextPath() + "/SvEquipoListar");
            return;

        } catch (NumberFormatException e) {
            String mensaje = URLEncoder.encode(
                    "Los títulos deben ser números.",
                    StandardCharsets.UTF_8
            );

            response.sendRedirect(
                    request.getContextPath() + "/SvEquipoListar?error=" + mensaje
            );
            return;

        } catch (Exception e) {
            String mensaje = URLEncoder.encode(
                    e.getMessage() != null ? e.getMessage() : "Error al crear el equipo.",
                    StandardCharsets.UTF_8
            );

            response.sendRedirect(
                    request.getContextPath() + "/SvEquipoListar?error=" + mensaje
            );
            return;
        }
    }
}
