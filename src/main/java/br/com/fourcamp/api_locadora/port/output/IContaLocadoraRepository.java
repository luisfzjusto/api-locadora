package br.com.fourcamp.api_locadora.port.output;

import br.com.fourcamp.api_locadora.domain.entity.ContaLocadora;

public interface IContaLocadoraRepository {
    void atualizarSaldo(ContaLocadora contaLocadora);
}
