package dao;

import model.Fornecedor;

import java.sql.*;
import java.util.ArrayList;

public class FornecedorDAO {
    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement prpstmt;
    private String status;
    public ArrayList<Fornecedor> getFornecedores(){
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        try (Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM fornecedor";
            this.stmt = connection.createStatement();
            this.rs= this.stmt.executeQuery(this.sql);

            while (this.rs.next()){
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(this.rs.getInt("id"));
                fornecedor.setNome(this.rs.getString("nome"));
                fornecedores.add(fornecedor);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return fornecedores;
    }

    public String cadastrar(Fornecedor fornecedor) {
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "INSERT INTO fornecedor (nome) VALUES (?)";

            this.prpstmt = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);

            this.prpstmt.setString(1, fornecedor.getNome());

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

    public String editar(Fornecedor fornecedor, String id) {
        try (Connection connection = new ConectaDB().getConexao()) {
            int converteId = Integer.parseInt(id);
            this.sql = "UPDATE fornecedor SET nome = ? WHERE id = ?";

            this.prpstmt = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);

            this.prpstmt.setString(1, fornecedor.getNome());
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
            this.sql = "DELETE FROM fornecedor WHERE id = ?";

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
