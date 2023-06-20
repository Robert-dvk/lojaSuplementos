package controller;

import dao.ClienteDAO;
import model.Cliente;
import service.ClienteService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("cliente-controller")
public class ClienteController extends HttpServlet {
    protected void service (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");
        String nome = req.getParameter("nome");
        String cpf = req.getParameter("cpf");
        Cliente c = new Cliente();
        if (opcao.equals("editar")) {
            String id = req.getParameter("id");
            if (new ClienteService().cadastrar(nome, cpf)) {
                c.setNome(nome);
                c.setCpf(cpf);
                new ClienteDAO().editar(c, id);
            }
        } else if (opcao.equals("excluir")) {
            String id = req.getParameter("id");
            new ClienteDAO().excluir(id);
        } else if (opcao.equals("cadastrar")){
            c.setNome(nome);
            c.setCpf(cpf);
            if (new ClienteService().cadastrar(nome, cpf)) {
                new ClienteDAO().cadastrar(c);
            } else {
                System.out.println("Nenhum campo pode ser vazio!");
            }
        }
        req.setAttribute("Clientes", new ClienteDAO().getClientes());
        RequestDispatcher rd = req.getRequestDispatcher("/clientes.jsp");
        rd.forward(req, resp);
    }
}