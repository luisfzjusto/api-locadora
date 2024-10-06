package br.com.fourcamp.api_locadora.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private ClienteDTO clienteDTO;
    private VeiculoDTO veiculoDTO;
    private String tipoReserva;
    private int quantidade;

}
