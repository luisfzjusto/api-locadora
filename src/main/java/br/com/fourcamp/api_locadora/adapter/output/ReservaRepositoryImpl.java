package br.com.fourcamp.api_locadora.adapter.output;

import br.com.fourcamp.api_locadora.domain.entity.Reserva;
import br.com.fourcamp.api_locadora.port.output.IReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReservaRepositoryImpl implements IReservaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void salvarReserva(Reserva reserva) {
        String sql = "SELECT public.realizar_reserva(?, ?, ?, ?)";
        jdbcTemplate.update(sql, reserva.getCliente().getId(), reserva.getVeiculo().getId(), reserva.getTipoReserva(), reserva.getQuantidade());
    }
}
