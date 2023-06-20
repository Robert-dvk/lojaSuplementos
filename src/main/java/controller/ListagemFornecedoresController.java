package controller;

import dao.FornecedorDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("fornecedores-visualizar")
public class ListagemFornecedoresController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("Fornecedores", new FornecedorDAO().getFornecedores());
        RequestDispatcher rd = req.getRequestDispatcher("/fornecedores.jsp");
        rd.forward(req, resp);
    }
}
