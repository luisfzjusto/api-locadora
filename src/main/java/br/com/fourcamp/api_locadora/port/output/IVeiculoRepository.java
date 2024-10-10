package br.com.fourcamp.api_locadora.port.output;

import br.com.fourcamp.api_locadora.domain.entity.Veiculo;

import java.util.List;
import java.util.Optional;

public interface IVeiculoRepository {
    void cadastrarVeiculo(Veiculo veiculo);

    void atualizarVeiculo(Veiculo veiculo);

    Optional<Veiculo> buscarVeiculoPorPlaca(String placa);

    List<Veiculo> listarVeiculosPorStatus(String status);

    void devolverVeiculo(String placa);
}
