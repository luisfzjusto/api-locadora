package br.com.fourcamp.api_locadora.port.output;

import br.com.fourcamp.api_locadora.domain.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteRepository {

    Cliente cadastrar(Cliente cliente);

    Optional<Cliente> buscarClientePorCPF(String cpf);

}
