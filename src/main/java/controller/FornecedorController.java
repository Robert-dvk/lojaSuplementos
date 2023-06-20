package controller;

import dao.FornecedorDAO;
import model.Fornecedor;
import service.FornecedorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("fornecedor-controller")
public class FornecedorController extends HttpServlet {
    protected void service (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");
        String nome = req.getParameter("nome");
        Fornecedor f = new Fornecedor();
        if (opcao.equals("editar")) {
            String id = req.getParameter("id");
            if (new FornecedorService().validar(nome)) {
                f.setNome(nome);
                new FornecedorDAO().editar(f, id);
            }
        } else if (opcao.equals("excluir")) {
            String id = req.getParameter("id");
            new FornecedorDAO().excluir(id);
        } else if (opcao.equals("cadastrar")){
            f.setNome(nome);
            if (new FornecedorService().validar(nome)) {
                new FornecedorDAO().cadastrar(f);
            } else {
                System.out.println("Nenhum campo pode ser vazio!");
            }
        }
        req.setAttribute("Fornecedores", new FornecedorDAO().getFornecedores());
        RequestDispatcher rd = req.getRequestDispatcher("/fornecedores.jsp");
        rd.forward(req, resp);
    }
}
