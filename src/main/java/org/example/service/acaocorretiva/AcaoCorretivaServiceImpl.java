package org.example.service.acaocorretiva;

import org.example.model.AcaoCorretiva;
import org.example.model.Equipamento;
import org.example.model.Falha;
import org.example.repository.AcaoCorretivaRepository;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;

public class AcaoCorretivaServiceImpl implements AcaoCorretivaService{

    AcaoCorretivaRepository repositoryA = new AcaoCorretivaRepository();
    FalhaRepository repositoryF = new FalhaRepository();
    EquipamentoRepository repositoryE = new EquipamentoRepository();
    Equipamento equipamento = new Equipamento();

    @Override
    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException {
        boolean existe = repositoryF.falhaExiste(acao.getFalhaId());

        if(existe == false){
            throw new RuntimeException();
        }

        Falha falha = repositoryF.buscarFalhaID(acao.getFalhaId());
        falha.setStatus("RESOLVIDA");



        if(falha.getStatus().equals("CRITICA")){
            repositoryE.atulizarEquipamento(falha.getEquipamentoId());
        }

        return repositoryA.criarAcaoCorretiva(acao);
    }
}
