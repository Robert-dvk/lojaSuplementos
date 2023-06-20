package teste;
import dao.ConectaDB;
import dao.UsuarioDAO;
import model.Usuario;
import model.Cliente;
import dao.ClienteDAO;

public class Teste {
    public static void main(String args[]){
        //testaGetClientes();
    }
    public static void testaGetClientes(){
        for (Cliente c : new ClienteDAO().getClientes()){
            System.out.println(c.getNome());
        }
    }
}
