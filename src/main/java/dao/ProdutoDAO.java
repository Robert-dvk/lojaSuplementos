package dao;

import model.Produto;

import java.sql.*;
import java.util.ArrayList;

public class ProdutoDAO {
    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement prpstmt;
    private String status;
    public ArrayList<Produto> getProdutos(){
        ArrayList<Produto> produtos = new ArrayList<>();
        try (Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM produto";
            this.stmt = connection.createStatement();
            this.rs= this.stmt.executeQuery(this.sql);

            while (this.rs.next()){
                Produto produto = new Produto();
                produto.setId(this.rs.getInt("id"));
                produto.setNome(this.rs.getString("nome"));
                produto.setPreco(this.rs.getDouble("preco"));
                produtos.add(produto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return produtos;
    }

    public String cadastrar(Produto produto) {
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "INSERT INTO produto (nome, preco) VALUES (?, ?)";

            this.prpstmt = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);

            this.prpstmt.setString(1, produto.getNome());
            this.prpstmt.setDouble(2, produto.getPreco());

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

    public String editar(Produto produto, String id) {
        try (Connection connection = new ConectaDB().getConexao()) {
            int converteId = Integer.parseInt(id);
            this.sql = "UPDATE produto SET nome = ?, preco = ? WHERE id = ?";

            this.prpstmt = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);

            this.prpstmt.setString(1, produto.getNome());
            this.prpstmt.setDouble(2, produto.getPreco());
            this.prpstmt.setInt(5, converteId);

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
            this.sql = "DELETE FROM produto WHERE id = ?";

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
