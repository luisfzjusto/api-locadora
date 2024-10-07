package br.com.fourcamp.api_locadora.domain.command;

import br.com.fourcamp.api_locadora.adapter.output.VeiculoRepositoryImpl;
import br.com.fourcamp.api_locadora.domain.dto.ReservaDTO;
import br.com.fourcamp.api_locadora.domain.entity.Cliente;
import br.com.fourcamp.api_locadora.domain.entity.Reserva;
import br.com.fourcamp.api_locadora.domain.entity.Veiculo;
import br.com.fourcamp.api_locadora.domain.strategy.PricingStrategy;
import br.com.fourcamp.api_locadora.port.input.IContaLocadoraService;
import br.com.fourcamp.api_locadora.port.input.IReservaService;
import br.com.fourcamp.api_locadora.port.output.IClienteRepository;
import br.com.fourcamp.api_locadora.port.output.IReservaRepository;
import br.com.fourcamp.api_locadora.port.output.IVeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements IReservaService {

    private final VeiculoRepositoryImpl veiculoRepository;
    private final IReservaRepository reservaRepository;
    private final IContaLocadoraService contaLocadoraService;
    private final PricingStrategy pricingStrategy;
    private final IClienteRepository clienteRepository;

    @Override
    public void realizarReserva(ReservaDTO reservaDTO) {
        Optional<Veiculo> veiculoOptional = veiculoRepository.buscarVeiculoPorPlaca(reservaDTO.getVeiculoDTO().getPlaca());
        if (veiculoOptional.isEmpty()) {
            throw new RuntimeException("Veículo não encontrado!");
        }
        Veiculo veiculo = veiculoOptional.get();

        Optional<Cliente> clienteOptional = clienteRepository.buscarClientePorCPF(reservaDTO.getClienteDTO().getCpf());
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado!");
        }
        Cliente cliente = clienteOptional.get();

        double valorTotal = pricingStrategy.calcularPreco(veiculo.getDiaria(), reservaDTO.getQuantidade());

        contaLocadoraService.adicionarSaldo(valorTotal);

        veiculo.setStatus("alugado");
        veiculoRepository.atualizarVeiculo(veiculo);

        Reserva reserva = new Reserva(cliente, veiculo, reservaDTO.getTipoReserva(), reservaDTO.getQuantidade());
        reservaRepository.salvarReserva(reserva);
    }
}
