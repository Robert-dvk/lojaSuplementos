package service;

public class FornecedorService {
    public boolean validar(String nome) {
        if (nome == null || nome.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
