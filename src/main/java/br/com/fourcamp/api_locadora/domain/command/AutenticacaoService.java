package br.com.fourcamp.api_locadora.domain.command;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // usuário chumbado no código
        if("admin".equals(username)){
            return new User("admin", "{noop}admin", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }
}
