package br.com.fourcamp.api_locadora.adapter.input;

import br.com.fourcamp.api_locadora.port.input.IContaLocadoraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locadora")
public class ContaLocadoraController {

    private final IContaLocadoraService contaLocadoraService;

    @GetMapping("/saldo")
    public ResponseEntity<Double> consultarSaldo(){
        double saldo = contaLocadoraService.consultarSaldo();
        return ResponseEntity.ok(saldo);
    }
}
