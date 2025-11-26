package org.example.service.equipamento;

import org.example.model.Equipamento;
import org.example.repository.EquipamentoRepository;

import java.sql.SQLException;

public class EquipamentoServiceImpl implements EquipamentoService{

    EquipamentoRepository repositoryE = new EquipamentoRepository();

    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {
        equipamento.setStatusOperacional("OPERACIONAL");
        return repositoryE.criarEquipamento(equipamento);
    }

    @Override
    public Equipamento buscarEquipamentoPorId(Long id) throws SQLException {
        boolean existe = repositoryE.equipamentoExiste(id);

        if(existe == true){
            Equipamento equipamento = repositoryE.BuscarEquipamentoPorID(id);
            return equipamento;
        }

        throw new RuntimeException("Equipamento não encontrado!");
    }
}
