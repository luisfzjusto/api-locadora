package br.com.fourcamp.api_locadora.adapter.output;

import br.com.fourcamp.api_locadora.domain.entity.Veiculo;
import br.com.fourcamp.api_locadora.port.output.IVeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VeiculoRepositoryImpl implements IVeiculoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Veiculo> veiculoMapper = (ResultSet rs, int rowNum) -> {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(rs.getLong("id"));
        veiculo.setMarca(rs.getString("marca"));
        veiculo.setModelo(rs.getString("modelo"));
        veiculo.setAno(rs.getInt("ano"));
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setDiaria(rs.getDouble("preco_por_dia"));
        veiculo.setCategoria(rs.getString("categoria"));
        veiculo.setStatus(rs.getString("status"));
        return veiculo;
    };

    @Override
    public void cadastrarVeiculo(Veiculo veiculo) {
        String sql = "SELECT cadastrar_veiculo(?,?,?,?,?,?)";
        Long veiculoId = jdbcTemplate.queryForObject(sql, Long.class, veiculo.getMarca(), veiculo.getModelo(), veiculo.getAno(), veiculo.getPlaca(), veiculo.getDiaria(), veiculo.getCategoria());
        veiculo.setId(veiculoId);
    }

    @Override
    public Optional<Veiculo> buscarVeiculoPorPlaca(String placa) {
        String sql = "SELECT * FROM buscar_veiculo_por_placa(?)";
        List<Veiculo> veiculos = jdbcTemplate.query(sql, veiculoMapper, placa);
        return veiculos.isEmpty() ? Optional.empty() : Optional.of(veiculos.get(0));
    }

    @Override
    public List<Veiculo> listarVeiculosPorStatus(String status) {
        String sql = "SELECT * FROM listar_veiculos_por_status(?)";
        return jdbcTemplate.query(sql, veiculoMapper, status);
    }

    @Override
    public void atualizarVeiculo(Veiculo veiculo){
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("atualizar_veiculo");

        Map<String, Object> param = new HashMap<>();

        param.put("id_veiculo", veiculo.getId());
        param.put("status_veiculo", veiculo.getStatus());
        call.execute(param);
    }
}
