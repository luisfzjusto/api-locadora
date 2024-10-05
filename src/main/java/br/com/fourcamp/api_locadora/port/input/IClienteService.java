package br.com.fourcamp.api_locadora.port.input;

import br.com.fourcamp.api_locadora.domain.dto.ClienteDTO;
import br.com.fourcamp.api_locadora.domain.entity.Cliente;

import java.util.Optional;

public interface IClienteService {

    Cliente cadastrarCliente(ClienteDTO clienteDTO);

    Optional<Cliente> buscarClientePorCPF(String cpf);

    }
