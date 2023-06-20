package controller;

import dao.CategoriaDAO;
import dao.FornecedorDAO;
import dao.ProdutoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "produtos-visualizar")
public class ListagemProdutosController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("Produtos", new ProdutoDAO().getProdutos());
        req.setAttribute("Fornecedores", new FornecedorDAO().getFornecedores());
        req.setAttribute("Categorias", new CategoriaDAO().getCategorias());
        RequestDispatcher rd = req.getRequestDispatcher("/produtos.jsp");
        rd.forward(req, resp);
    }
}
