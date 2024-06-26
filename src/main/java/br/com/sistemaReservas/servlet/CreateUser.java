package br.com.sistemaReservas.servlet;
import br.com.sistemaReservas.dao.DAOCliente;
import br.com.sistemaReservas.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-user")
public class CreateUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String userName = request.getParameter("nome");
        String userEmail = request.getParameter("email");
        String userCpf = request.getParameter("cpf");
        String userTelefone = request.getParameter("telefone");
        String userSenha = request.getParameter("senha");

        DAOCliente userDAO = new DAOCliente();

        try {
            userDAO.saveUser(new User(userName, userEmail, userCpf, userTelefone, userSenha));
            request.getRequestDispatcher("/pages/login-register/login.html").forward(request, resp);
        } catch (DAOCliente.EmailAlreadyInUseException e) {
            // usado para mandar um alerta e voltar para tela de registro novamente
            resp.getWriter().println("<script>alert('Email já está em uso. Por favor, tente outro email.');</script>");
            // request.getRequestDispatcher("/pages/login-register/register.html").forward(request, resp);
        }
    }

}
