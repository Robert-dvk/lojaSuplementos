package controller;

import dao.CategoriaDAO;
import model.Categoria;
import service.CategoriaService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("categoria-controller")
public class CategoriaController extends HttpServlet {
    protected void service (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");
        String nome = req.getParameter("nome");
        Categoria c = new Categoria();
        if (opcao.equals("editar")) {
            String id = req.getParameter("id");
            if (new CategoriaService().validar(nome)) {
                c.setNome(nome);
                new CategoriaDAO().editar(c, id);
            }
        } else if (opcao.equals("excluir")) {
            String id = req.getParameter("id");
            new CategoriaDAO().excluir(id);
        } else if (opcao.equals("cadastrar")){
            c.setNome(nome);
            if (new CategoriaService().validar(nome)) {
                new CategoriaDAO().cadastrar(c);
            } else {
                System.out.println("Nenhum campo pode ser vazio!");
            }
        }
        req.setAttribute("Categorias", new CategoriaDAO().getCategorias());
        RequestDispatcher rd = req.getRequestDispatcher("/categorias.jsp");
        rd.forward(req, resp);
    }
}
