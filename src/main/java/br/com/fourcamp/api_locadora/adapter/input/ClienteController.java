package br.com.fourcamp.api_locadora.adapter.input;

import br.com.fourcamp.api_locadora.domain.command.ClienteServiceImpl;
import br.com.fourcamp.api_locadora.domain.dto.ClienteDTO;
import br.com.fourcamp.api_locadora.domain.entity.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServiceImpl clienteServiceImpl;

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteServiceImpl.cadastrarCliente(clienteDTO);
        return ResponseEntity.ok(cliente);
    }


}
