    package br.com.fourcamp.api_locadora.adapter.input;

    import br.com.fourcamp.api_locadora.domain.command.ReservaServiceImpl;
    import br.com.fourcamp.api_locadora.domain.dto.ReservaDTO;
    import br.com.fourcamp.api_locadora.port.input.IReservaService;
    import br.com.fourcamp.api_locadora.port.input.IVeiculoService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.Map;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/reservas")
    public class ReservaController {

        private final IReservaService reservaService;

        private final IVeiculoService veiculoService;

        @PostMapping("/realizar")
        public ResponseEntity<String> realizarReserva(@RequestBody ReservaDTO reservaDTO) {
            reservaService.realizarReserva(reservaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Reserva realizada com sucesso!");
        }

        @PostMapping("/devolver")
        public ResponseEntity<String> devolverVeiculo(@RequestBody Map<String, String> request) {
            String placa = request.get("placa");
            if (placa != null && !placa.isEmpty()) {
                veiculoService.devolverVeiculo(placa);
                return ResponseEntity.ok("Veículo placa: " + placa + " devolvido com sucesso.");
            } else {
                return ResponseEntity.badRequest().body("Placa do veículo não informada.");
            }
        }
    }

