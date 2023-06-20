package controller;

import dao.FornecedorDAO;
import dao.ProdutoDAO;
import model.Produto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("produto-controller")
public class ProdutoController extends HttpServlet {
    protected void service (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");
        Produto p = new Produto();
        if (opcao.equals("editar")) {
            String nome = req.getParameter("nome");
            String preco = req.getParameter("preco");
            Double precoConvertido = Double.parseDouble(preco);
            String id = req.getParameter("id");
            p.setNome(nome);
            p.setPreco(precoConvertido);
            new  ProdutoDAO().editar(p, id);
        } else if (opcao.equals("excluir")) {
            String id = req.getParameter("id");
            new ProdutoDAO().excluir(id);
        } else if (opcao.equals("cadastrar")){
            String nome = req.getParameter("nome");
            String preco = req.getParameter("preco");
            Double precoConvertido = Double.parseDouble(preco);
            p.setNome(nome);
            p.setPreco(precoConvertido);
            new  ProdutoDAO().cadastrar(p);
          }
        req.setAttribute("Produtos", new ProdutoDAO().getProdutos());
        RequestDispatcher rd = req.getRequestDispatcher("/produtos.jsp");
        rd.forward(req, resp);
    }
}
