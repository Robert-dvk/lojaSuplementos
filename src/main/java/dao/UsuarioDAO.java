package dao;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
public class UsuarioDAO {
    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pt;
    public Usuario getUsuario (String login) {
        Usuario usuario = new Usuario();
        try (Connection con = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM usuario WHERE login = ?";

            this.pt = con.prepareStatement(this.sql);
            this.pt.setString(1, login);
            this.rs = this.pt.executeQuery();

            while (this.rs.next()) {
                usuario.setId(this.rs.getInt("id"));
                usuario.setLogin(this.rs.getString("login"));
                usuario.setSenha(this.rs.getString("senha"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
