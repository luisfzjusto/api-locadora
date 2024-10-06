package br.com.fourcamp.api_locadora.domain.command;

import br.com.fourcamp.api_locadora.domain.entity.ContaLocadora;
import br.com.fourcamp.api_locadora.port.input.IContaLocadoraService;
import br.com.fourcamp.api_locadora.port.output.IContaLocadoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContaLocadoraServiceImpl implements IContaLocadoraService {

    private final IContaLocadoraRepository contaLocadoraRepository;
    private ContaLocadora contaLocadora;

    @Transactional
    @Override
    public void adicionarSaldo(double valor) {
        contaLocadora.adicionarSaldo(valor);
        contaLocadoraRepository.atualizarSaldo(contaLocadora);
    }
}
