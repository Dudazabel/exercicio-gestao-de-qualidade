package org.example.service.falha;

import org.example.model.Falha;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;
import java.util.List;

public class FalhaServiceImpl implements FalhaService {

    FalhaRepository repositoryF = new FalhaRepository();
    EquipamentoRepository repositoryE = new EquipamentoRepository();

    @Override
    public Falha registrarNovaFalha(Falha falha) throws SQLException {

        if (!repositoryE.equipamentoExiste(falha.getEquipamentoId())) {
            throw new IllegalArgumentException("Equipamento não encontrado!");
        }

        falha.setStatus("ABERTA");

        if (falha.getCriticidade().equals("CRITICA")) {
            repositoryE.atualizarStatusCritica(falha.getEquipamentoId());
        }

        return repositoryF.criarFalha(falha);
    }

    @Override
    public List<Falha> buscarFalhasCriticasAbertas() throws SQLException {
        return repositoryF.listaFalhaCriticaAberta();
    }
}
