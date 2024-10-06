package br.com.fourcamp.api_locadora.port.input;

import br.com.fourcamp.api_locadora.domain.dto.ReservaDTO;

public interface IReservaService {
    void realizarReserva(ReservaDTO reservaDTO);
}
