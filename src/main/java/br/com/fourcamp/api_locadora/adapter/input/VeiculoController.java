package br.com.fourcamp.api_locadora.adapter.input;

import br.com.fourcamp.api_locadora.domain.command.VeiculoServiceImpl;
import br.com.fourcamp.api_locadora.domain.dto.VeiculoDTO;
import br.com.fourcamp.api_locadora.domain.entity.Veiculo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoServiceImpl veiculoServiceimpl;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarVeiculo(@RequestBody VeiculoDTO veiculoDTO){
        veiculoServiceimpl.cadastrarVeiculo(veiculoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Veículo cadastrado com sucesso!");
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<?> buscarClientePorPlaca(@PathVariable String placa){
        Optional<Veiculo> veiculo = veiculoServiceimpl.buscarVeiculoPorPlaca(placa);
        if (veiculo.isPresent()){
            return ResponseEntity.ok(veiculo.get());
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não encontrado!");
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> listarVeiculosPorStatus(@PathVariable String status){
        List<Veiculo> veiculos = veiculoServiceimpl.listarVeiculosPorStatus(status);
        if(veiculos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A consulta não encontrou registros");
        }
        return new ResponseEntity<>(veiculos, HttpStatus.OK);
    }
}
