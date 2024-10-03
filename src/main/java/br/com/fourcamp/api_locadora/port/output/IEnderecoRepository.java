package br.com.fourcamp.api_locadora.port.output;

import br.com.fourcamp.api_locadora.domain.entity.Endereco;

public interface IEnderecoRepository {
    Endereco cadastrar(Endereco endereco);

    Endereco buscarPorId(Long id);
}
