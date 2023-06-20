package service;

public class ClienteService {
    public boolean cadastrar(String nome, String cpf) {
        if (nome == null || cpf == null || nome.isEmpty() || cpf.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}