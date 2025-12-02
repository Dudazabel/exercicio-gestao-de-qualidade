package org.example.repository;

import org.example.database.Conexao;
import org.example.model.Equipamento;

import java.sql.*;

public class EquipamentoRepository {

    public Equipamento criarEquipamento(Equipamento equipamento)throws SQLException{
        String query = "INSERT INTO Equipamento (nome, numeroDeSerie, areaSetor, statusOperacional) VALUES (?,?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroDeSerie());
            stmt.setString(3, equipamento.getAreaSetor());
            stmt.setString(4, equipamento.getStatusOperacional());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                equipamento.setId(rs.getLong(1));
            }
        }

        return equipamento;
    }

    public boolean equipamentoExiste(Long id) throws  SQLException{
        String query = "SELECT nome FROM Equipamento WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return true;
            }
        }

        return false;
    }

    public Equipamento BuscarEquipamentoPorID(long id) throws SQLException{
        String query = "SELECT id, nome, numeroDeSerie, areaSetor, statusOperacional FROM Equipamento WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Long ID = rs.getLong("id");
                String nome = rs.getString("nome");
                String numeroDeSerie = rs.getString("numeroDeSerie");
                String areaSetor = rs.getString("areaSetor");
                String statusOperacional = rs.getString("statusOperacional");

                var equipamento = new Equipamento(ID, nome, numeroDeSerie, areaSetor, statusOperacional);
                return equipamento;
            }
        }

        return null;
    }

    public void atualizarStatusCritica(Long id) throws SQLException{
        String query = "UPDATE Equipamento SET statusOperacional = 'EM_MANUTENCAO'  WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public void atulizarEquipamento(Long id) throws SQLException{
        String query = "UPDATE Equipamento SET statusOperacional = 'OPERACIONAL' WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
