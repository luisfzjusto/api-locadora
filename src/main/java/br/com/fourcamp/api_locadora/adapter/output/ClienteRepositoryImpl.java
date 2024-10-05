package br.com.fourcamp.api_locadora.adapter.output;

import br.com.fourcamp.api_locadora.domain.entity.Cliente;
import br.com.fourcamp.api_locadora.domain.entity.Endereco;
import br.com.fourcamp.api_locadora.port.output.IClienteRepository;
import br.com.fourcamp.api_locadora.port.output.IEnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryImpl implements IClienteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Cliente> clienteMapper = (ResultSet rs, int rowNum) -> {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        cliente.setTelefone(rs.getString("telefone"));

        Endereco endereco = new Endereco();
        endereco.setLogradouro(rs.getString("logradouro"));
        endereco.setNumero(rs.getString("numero"));
        endereco.setBairro(rs.getString("bairro"));
        endereco.setCidade(rs.getString("cidade"));
        endereco.setUf(rs.getString("uf"));
        endereco.setCep(rs.getString("cep"));

        cliente.setEndereco(endereco);

        return cliente;
    };

    @Override
    public Cliente cadastrar(Cliente cliente){
        Integer idEndereco = cliente.getEndereco().getId().intValue();
        String sql = "SELECT cadastrar_cliente (?,?,?,?,?)";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getTelefone(), idEndereco);
        cliente.setId(Long.valueOf(id));
        return cliente;
    }

    @Override
    public Optional<Cliente> buscarClientePorCPF(String cpf){
        String sql = "SELECT * FROM buscar_cliente_por_cpf(?)";
        List<Cliente> clientes = jdbcTemplate.query(sql, clienteMapper, cpf);
        return clientes.isEmpty() ? Optional.empty() : Optional.of(clientes.get(0));
    }
}
