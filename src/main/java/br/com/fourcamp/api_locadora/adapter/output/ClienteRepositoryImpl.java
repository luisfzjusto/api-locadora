package br.com.fourcamp.api_locadora.adapter.output;

import br.com.fourcamp.api_locadora.domain.entity.Cliente;
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

    @Autowired
    private IEnderecoRepository IEnderecoRepository;

    private final RowMapper<Cliente> clienteMapper = (ResultSet rs, int rowNum) -> {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        cliente.setTelefone(rs.getString("telefone"));
        // Chama função para obter endereço do cliente
        Long idEndereco = rs.getLong("id_endereco");
        cliente.setEndereco(IEnderecoRepository.buscarPorId(idEndereco));
        return cliente;
    };

    @Override
    public Cliente cadastrar(Cliente cliente){
        String sql = "SELECT cadastrar_cliente (?,?,?,?,?)";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getTelefone(), cliente.getEndereco().getId());
        cliente.setId(Long.valueOf(id));
        return cliente;
    }

    @Override
    public Optional<Cliente> buscarClientePorCPF(String cpf){
        String sql = "SELECT * FROM buscar_cliente_por_cpf(?)";
        List<Cliente> clientes = jdbcTemplate.query(sql, clienteMapper, cpf);
        return clientes.isEmpty() ? Optional.empty() : Optional.of(clientes.get(0));
    }

    @Override
    public List<Cliente> listarClientes(){
        String sql = "SELECT * FROM cliente";
        return jdbcTemplate.query(sql, clienteMapper);
    }
}
