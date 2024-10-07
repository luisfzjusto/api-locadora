package br.com.fourcamp.api_locadora.adapter.output;

import br.com.fourcamp.api_locadora.domain.entity.Reserva;
import br.com.fourcamp.api_locadora.port.output.IReservaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.stereotype.Repository;
import org.springframework.ui.context.support.ResourceBundleThemeSource;

@Repository
public class ReservaRepositoryImpl implements IReservaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ReservaRepositoryImpl.class);

    @Override
    public void salvarReserva(Reserva reserva) {
        try{
        String sql = "CALL public.realizar_reserva(?, ?, ?, ?)";
        jdbcTemplate.execute(sql, (PreparedStatementCallback<Void>) pstmt -> {
            pstmt.setInt(1, reserva.getCliente().getId().intValue());
            pstmt.setInt(2, reserva.getVeiculo().getId().intValue());
            pstmt.setString(3, reserva.getTipoReserva());
            pstmt.setInt(4, reserva.getQuantidade());
            pstmt.execute();
            return null;
        });} catch (DataAccessException e){
            logger.error("passando aqui");
            System.out.println(e.getMostSpecificCause().getMessage());
        }

    }

    /*@Override
    public void salvarReserva(Reserva reserva) {
        String sql = "{CALL public.realizar_reserva(?, ?, ?, ?)}";
        jdbcTemplate.update(sql, reserva.getCliente().getId(), reserva.getVeiculo().getId(), reserva.getTipoReserva(), reserva.getQuantidade());
    }*/
}

