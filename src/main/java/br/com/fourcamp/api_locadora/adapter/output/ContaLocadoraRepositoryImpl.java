package br.com.fourcamp.api_locadora.adapter.output;

import br.com.fourcamp.api_locadora.domain.entity.ContaLocadora;
import br.com.fourcamp.api_locadora.port.output.IContaLocadoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ContaLocadoraRepositoryImpl implements IContaLocadoraRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void atualizarSaldo(ContaLocadora contaLocadora) {
        String sql = "CALL atualizar_saldo_locadora(?)";
        jdbcTemplate.update(sql, contaLocadora.getSaldo());
    }

    @Override
    public void salvar(ContaLocadora contaLocadora) {
        String sql = "UPDATE conta_locadora SET saldo = ? WHERE id = ?";
        jdbcTemplate.update(sql, contaLocadora.getSaldo(), contaLocadora.getId());
    }

    @Override
    public Optional<ContaLocadora> findById(Long id) {
        String sql = "SELECT id, saldo FROM conta_locadora WHERE id = ?";
        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                ContaLocadora contaLocadora = new ContaLocadora();
                contaLocadora.setId(rs.getLong("id"));
                contaLocadora.setSaldo(rs.getDouble("saldo"));
                return Optional.of(contaLocadora);
            }
            return Optional.empty();
        }, id);
    }

    @Override
    public double consultarSaldo() {
        String sql = "SELECT saldo FROM conta_locadora WHERE id = 1";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }
}
