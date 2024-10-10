package br.com.fourcamp.api_locadora.domain.command;

import br.com.fourcamp.api_locadora.domain.dto.VeiculoDTO;
import br.com.fourcamp.api_locadora.domain.entity.Veiculo;
import br.com.fourcamp.api_locadora.port.input.IVeiculoService;
import br.com.fourcamp.api_locadora.port.output.IVeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class VeiculoServiceImpl implements IVeiculoService {

    private final IVeiculoRepository veiculoRepository;
    @Override
    public void cadastrarVeiculo(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setAno(veiculoDTO.getAno());
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setDiaria(veiculoDTO.getDiaria());
        veiculo.setCategoria(veiculoDTO.getCategoria());
        veiculo.setStatus("disponível");

        veiculoRepository.cadastrarVeiculo(veiculo);
    }

    @Override
    public Optional<Veiculo> buscarVeiculoPorPlaca(String placa) {
        return veiculoRepository.buscarVeiculoPorPlaca(placa);
    }

    @Override
    public List<Veiculo> listarVeiculosPorStatus(String status) {
        return veiculoRepository.listarVeiculosPorStatus(status);
    }

    @Override
    public void devolverVeiculo(String placa) {
        veiculoRepository.devolverVeiculo(placa);
    }
}
