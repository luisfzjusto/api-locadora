package br.com.fourcamp.api_locadora.port.output;

import br.com.fourcamp.api_locadora.domain.entity.ContaLocadora;

import java.util.Optional;

public interface IContaLocadoraRepository {
    void atualizarSaldo(ContaLocadora contaLocadora);

    Optional<ContaLocadora> findById(Long id);

    void salvar(ContaLocadora contaLocadora);

    double consultarSaldo();
}
