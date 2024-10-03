package br.com.fourcamp.api_locadora.adapter.output;

import br.com.fourcamp.api_locadora.domain.entity.Endereco;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoMapper implements RowMapper<Endereco> {
    @Override
    public Endereco mapRow(ResultSet rs, int rowNum) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setId(rs.getLong("id"));
        endereco.setLogradouro(rs.getString("logradouro"));
        endereco.setNumero(rs.getString("numero"));
        endereco.setBairro(rs.getString("bairro"));
        endereco.setCidade(rs.getString("cidade"));
        endereco.setUf(rs.getString("uf"));
        endereco.setCep(rs.getString("cep"));
        return endereco;
    }
}
