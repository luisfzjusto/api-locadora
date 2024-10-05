package br.com.fourcamp.api_locadora.adapter.output;

import br.com.fourcamp.api_locadora.domain.entity.Endereco;
import br.com.fourcamp.api_locadora.port.output.IEnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EnderecoRepositoryImpl implements IEnderecoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Endereco cadastrar(Endereco endereco){
        String sql = "SELECT cadastrar_endereco(?, ?, ?, ?, ?, ?)";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, endereco.getLogradouro(), endereco.getNumero(), endereco.getBairro(), endereco.getCidade(), endereco.getUf(), endereco.getCep());
        endereco.setId(Long.valueOf(id));
        return endereco;
    }

    @Override
    public Endereco buscarPorId(Long id){
        String sql = "SELECT * FROM endereco WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new EnderecoMapper(), id);
    }


}
