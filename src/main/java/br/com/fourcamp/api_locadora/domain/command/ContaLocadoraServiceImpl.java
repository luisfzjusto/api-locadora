package br.com.fourcamp.api_locadora.domain.command;

import br.com.fourcamp.api_locadora.domain.entity.ContaLocadora;
import br.com.fourcamp.api_locadora.port.input.IContaLocadoraService;
import br.com.fourcamp.api_locadora.port.output.IContaLocadoraRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContaLocadoraServiceImpl implements IContaLocadoraService {

    private final IContaLocadoraRepository contaLocadoraRepository;
    private ContaLocadora contaLocadora;

    @PostConstruct
    public void init() {
        // Carregar ou criar a conta locadora com ID 1
        contaLocadora = contaLocadoraRepository.findById(1L)
                .orElseGet(() -> {
                    ContaLocadora novaConta = new ContaLocadora();
                    novaConta.setId(1L);
                    novaConta.setSaldo(0.0); // Saldo inicial
                    contaLocadoraRepository.salvar(novaConta);
                    return novaConta;
                });
    }

    @Transactional
    @Override
    public void adicionarSaldo(double valor) {
        contaLocadora.adicionarSaldo(valor);
        contaLocadoraRepository.atualizarSaldo(contaLocadora);
    }

    @Override
    public double consultarSaldo(){
        return contaLocadora.getSaldo();
    }
}
