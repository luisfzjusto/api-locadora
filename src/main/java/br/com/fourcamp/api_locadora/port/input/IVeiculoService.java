package br.com.fourcamp.api_locadora.port.input;

import br.com.fourcamp.api_locadora.domain.dto.VeiculoDTO;
import br.com.fourcamp.api_locadora.domain.entity.Veiculo;

import java.util.List;
import java.util.Optional;

public interface IVeiculoService {

    Veiculo cadastrarVeiculo(VeiculoDTO veiculoDTO);

    Optional<Veiculo> buscarVeiculoPorPlaca(String placa);

    List<Veiculo> listarVeiculosDisponiveis();

    List<Veiculo> listarVeiculosAlugados();
}
