
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

@WebServlet(name = "SvEquipoEditar", urlPatterns = {"/SvEquipoEditar"})
public class SvEquipoEditar extends HttpServlet {

    private final ControladoraLogica controlLogica = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Equipo equipo = controlLogica.buscarUnEquipo(id);

            if (equipo == null) {
                redirigirConError(request, response,
                        "El equipo solicitado no existe.");
                return;
            }

            request.setAttribute("equipo", equipo);
            request.getRequestDispatcher("/WEB-INF/css/equipoEditar.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {
            redirigirConError(request, response, "El ID del equipo es inválido.");
        } catch (Exception e) {
            if (!response.isCommitted()) {
                redirigirConError(request, response,
                        "No se pudo cargar el equipo.");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String ciudad = request.getParameter("ciudad");
            String estadio = request.getParameter("estadio");
            int titulos = Integer.parseInt(request.getParameter("titulos"));

            Equipo equipoModificado = new Equipo(
                    id, nombre, ciudad, estadio, titulos
            );

            controlLogica.editarEquipo(equipoModificado);

            response.sendRedirect(
                    request.getContextPath() + "/SvEquipoListar"
            );

        } catch (NumberFormatException e) {
            redirigirConError(request, response,
                    "El ID y la cantidad de títulos deben ser números.");
        } catch (Exception e) {
            String mensaje = e.getMessage();

            if (mensaje == null || mensaje.trim().isEmpty()) {
                mensaje = "No se pudo editar el equipo.";
            }

            redirigirConError(request, response, mensaje);
        }
    }

    private void redirigirConError(HttpServletRequest request,
                                   HttpServletResponse response,
                                   String mensaje) throws IOException {

        String mensajeCodificado = URLEncoder.encode(
                mensaje, StandardCharsets.UTF_8
        );

        response.sendRedirect(
                request.getContextPath()
                + "/SvEquipoListar?error=" + mensajeCodificado
        );
    }
}
