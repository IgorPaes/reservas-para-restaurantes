package br.com.sistemaReservas.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistemaReservas.dao.DAOReserva;
import br.com.sistemaReservas.model.Reserva;

@WebServlet("/historico-reservas")
public class CatchHistoricoReservas extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DAOReserva daoReserva = new DAOReserva(); 
        List<Reserva> reservas = daoReserva.capturaReservas();

        if (reservas != null) {
            request.setAttribute("reservas", reservas);
            request.getRequestDispatcher("/pages/gerenciamento/cliente/historico/historico.jsp").forward(request, response);
        } else {
            System.out.println("Nenhuma reserva foi encontrada!");
        }
        
    }
    
}