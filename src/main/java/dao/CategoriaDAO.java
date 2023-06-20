package dao;

import model.Categoria;

import java.sql.*;
import java.util.ArrayList;

public class CategoriaDAO {
    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement prpstmt;
    private String status;
    public ArrayList<Categoria> getCategorias(){
        ArrayList<Categoria> categorias = new ArrayList<>();
        try (Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM categoria";
            this.stmt = connection.createStatement();
            this.rs= this.stmt.executeQuery(this.sql);

            while (this.rs.next()){
                Categoria categoria = new Categoria();
                categoria.setId(this.rs.getInt("id"));
                categoria.setNome(this.rs.getString("nome"));
                categorias.add(categoria);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return categorias;
    }

    public String cadastrar(Categoria categoria) {
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "INSERT INTO categoria (nome) VALUES (?)";

            this.prpstmt = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);

            this.prpstmt.setString(1, categoria.getNome());

            this.prpstmt.execute();
            this.rs = this.prpstmt.getGeneratedKeys();
            this.rs.next();

            if (this.rs.getInt(1) > 1) {
                this.status = "Ok";
            } else {
                System.out.println(this.sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.status;
    }

    public String editar(Categoria categoria, String id) {
        try (Connection connection = new ConectaDB().getConexao()) {
            int converteId = Integer.parseInt(id);
            this.sql = "UPDATE categoria SET nome = ? WHERE id = ?";

            this.prpstmt = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);

            this.prpstmt.setString(1, categoria.getNome());
            this.prpstmt.setInt(2, converteId);

            this.prpstmt.execute();
            this.rs = this.prpstmt.getGeneratedKeys();
            this.rs.next();

            if (this.rs.getInt(1) > 1) {
                this.status = "Ok";
            } else {
                System.out.println(this.sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.status;
    }
    public String excluir (String id) {
        try (Connection connection = new ConectaDB().getConexao()) {
            int converteId = Integer.parseInt(id);
            this.sql = "DELETE FROM categoria WHERE id = ?";

            this.prpstmt = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);

            this.prpstmt.setInt(1, converteId);

            this.prpstmt.execute();
            this.rs = this.prpstmt.getGeneratedKeys();
            this.rs.next();
            if (this.rs.getInt(1) > 1) {
                this.status = "Ok";
            } else {
                System.out.println(this.sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.status;
    }
}
