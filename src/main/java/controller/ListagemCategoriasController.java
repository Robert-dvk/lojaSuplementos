package controller;

import dao.CategoriaDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("categorias-visualizar")
public class ListagemCategoriasController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("Categorias", new CategoriaDAO().getCategorias());
        RequestDispatcher rd = req.getRequestDispatcher("/categorias.jsp");
        rd.forward(req, resp);
    }
}
