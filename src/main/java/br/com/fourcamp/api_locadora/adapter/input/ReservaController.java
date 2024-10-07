package br.com.fourcamp.api_locadora.adapter.input;

import br.com.fourcamp.api_locadora.domain.command.ReservaServiceImpl;
import br.com.fourcamp.api_locadora.domain.dto.ReservaDTO;
import br.com.fourcamp.api_locadora.port.input.IReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservas")
public class ReservaController {

    private final IReservaService reservaService;

    @PostMapping("/realizar")
    public ResponseEntity<String> realizarReserva(@RequestBody ReservaDTO reservaDTO) {
        reservaService.realizarReserva(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reserva realizada com sucesso!");
    }
}

