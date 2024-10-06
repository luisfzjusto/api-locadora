package br.com.fourcamp.api_locadora.adapter.output;

import br.com.fourcamp.api_locadora.domain.entity.ContaLocadora;
import br.com.fourcamp.api_locadora.port.output.IContaLocadoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ContaLocadoraRepositoryImpl implements IContaLocadoraRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void atualizarSaldo(ContaLocadora contaLocadora) {
        String sql = "SELECT atualizar_saldo_locadora(?)";
        jdbcTemplate.update(sql, contaLocadora.getSaldo());
    }
}
