package service;

public class CategoriaService {
    public boolean validar(String nome) {
        if (nome == null || nome.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
