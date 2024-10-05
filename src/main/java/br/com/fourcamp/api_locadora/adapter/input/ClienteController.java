package br.com.fourcamp.api_locadora.adapter.input;

import br.com.fourcamp.api_locadora.domain.command.ClienteServiceImpl;
import br.com.fourcamp.api_locadora.domain.dto.ClienteDTO;
import br.com.fourcamp.api_locadora.domain.entity.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServiceImpl clienteServiceImpl;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteServiceImpl.cadastrarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso!");
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> buscarClientePorCPF(@PathVariable String cpf){
        Optional<Cliente> cliente = clienteServiceImpl.buscarClientePorCPF(cpf);
        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
    }

}
