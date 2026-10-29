
package com.app.servlets;

import com.app.logica.Jugador;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SvJugadorListar", urlPatterns = {"/SvJugadorListar"})
public class SvJugadorListar extends HttpServlet {

    private ControladoraLogica controlLogica = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String posicion = request.getParameter("posicion");
        List<Jugador> lista;

        if (posicion != null && !posicion.trim().isEmpty()) {
            lista = controlLogica.buscarJugadoresPorPosicion(posicion.trim());
        } else {
            lista = controlLogica.listarJugadores();
        }

        request.setAttribute("jugadores", lista);
        request.setAttribute("posicionBuscada", posicion);

        request.getRequestDispatcher("/WEB-INF/css/jugador.jsp")
               .forward(request, response);
    }
}
