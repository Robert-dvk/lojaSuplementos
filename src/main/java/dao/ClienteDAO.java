package dao;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;


public class ClienteDAO {
    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement prpstmt;
    private String status;
    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        try (Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM cliente";
            this.stmt = connection.createStatement();
            this.rs= this.stmt.executeQuery(this.sql);

            while (this.rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(this.rs.getInt("id"));
                cliente.setNome(this.rs.getString("nome"));
                cliente.setCpf(this.rs.getString("cpf"));
                clientes.add(cliente);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }

    public String cadastrar(Cliente cliente) {
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "INSERT INTO cliente (nome, cpf) VALUES (?, ?)";

            this.prpstmt = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);

            this.prpstmt.setString(1, cliente.getNome());
            this.prpstmt.setString(2, cliente.getCpf());

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

    public String editar(Cliente cliente, String id) {
        try (Connection connection = new ConectaDB().getConexao()) {
            int converteId = Integer.parseInt(id);
            this.sql = "UPDATE cliente SET nome = ?, cpf = ? WHERE id = ?";

            this.prpstmt = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);

            this.prpstmt.setString(1, cliente.getNome());
            this.prpstmt.setString(2, cliente.getCpf());
            this.prpstmt.setInt(3, converteId);

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
            this.sql = "DELETE FROM cliente WHERE id = ?";

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
