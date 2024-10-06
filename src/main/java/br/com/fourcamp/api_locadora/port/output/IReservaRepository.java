package br.com.fourcamp.api_locadora.port.output;

import br.com.fourcamp.api_locadora.domain.entity.Reserva;

public interface IReservaRepository {
    void salvarReserva(Reserva reserva);
}
