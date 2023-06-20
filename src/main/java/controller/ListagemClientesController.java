package controller;

import dao.ClienteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "clientes-visualizar")
public class ListagemClientesController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("Clientes", new ClienteDAO().getClientes());
        RequestDispatcher rd = req.getRequestDispatcher("/clientes.jsp");
        rd.forward(req, resp);
    }
}
