package br.com.fourcamp.api_locadora.domain.command;

import br.com.fourcamp.api_locadora.domain.dto.ReservaDTO;
import br.com.fourcamp.api_locadora.domain.entity.Reserva;
import br.com.fourcamp.api_locadora.domain.entity.Veiculo;
import br.com.fourcamp.api_locadora.domain.strategy.PricingStrategy;
import br.com.fourcamp.api_locadora.port.input.IContaLocadoraService;
import br.com.fourcamp.api_locadora.port.input.IReservaService;
import br.com.fourcamp.api_locadora.port.output.IReservaRepository;
import br.com.fourcamp.api_locadora.port.output.IVeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements IReservaService {

    private final IVeiculoRepository veiculoRepository;
    private final IReservaRepository reservaRepository;
    private final IContaLocadoraService contaLocadoraService;
    private final PricingStrategy pricingStrategy;

    @Override
    public void realizarReserva(ReservaDTO reservaDTO) {
        Veiculo veiculo = veiculoRepository.buscarVeiculoPorPlaca(reservaDTO.getVeiculoDTO().getPlaca());
        double valorTotal = pricingStrategy.calcularPreco(veiculo.getDiaria(), reservaDTO.getQuantidade());

        contaLocadoraService.adicionarSaldo(valorTotal);

        veiculo.setStatus("alugado");
        veiculoRepository.atualizarVeiculo(veiculo);

        Reserva reserva = new Reserva(veiculo, reservaDTO.getClienteDTO(), reservaDTO.getTipoReserva(), reservaDTO.getQuantidade());
        reservaRepository.salvarReserva(reserva);
    }
}
