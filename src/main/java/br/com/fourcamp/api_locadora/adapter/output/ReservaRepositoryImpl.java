package br.com.fourcamp.api_locadora.adapter.output;

import br.com.fourcamp.api_locadora.domain.entity.Reserva;
import br.com.fourcamp.api_locadora.port.output.IReservaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

@Repository
public class ReservaRepositoryImpl implements IReservaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void salvarReserva(Reserva reserva) {
        String sql = "SELECT public.realizar_reserva(?, ?, ?, ?)";
        jdbcTemplate.execute(sql, (PreparedStatementCallback<Void>) pstmt -> {
            pstmt.setLong(1, reserva.getCliente().getId());
            pstmt.setLong(2, reserva.getVeiculo().getId());
            pstmt.setString(3, reserva.getTipoReserva());
            pstmt.setInt(4, reserva.getQuantidade());
            pstmt.execute();
            return null;
        });
    }

    /*@Override
    public void salvarReserva(Reserva reserva) {
        String sql = "SELECT public.realizar_reserva(?, ?, ?, ?)";
        jdbcTemplate.update(sql, reserva.getCliente().getId(), reserva.getVeiculo().getId(), reserva.getTipoReserva(), reserva.getQuantidade());
    }*/
}

