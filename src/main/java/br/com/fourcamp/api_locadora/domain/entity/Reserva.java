package br.com.fourcamp.api_locadora.domain.entity;

import br.com.fourcamp.api_locadora.domain.strategy.PricingStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    private int id;
    private Cliente cliente;
    private Veiculo veiculo;
    private String tipoReserva;
    private int quantidade;
}
