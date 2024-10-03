package br.com.fourcamp.api_locadora.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private String nome;

    private String cpf;

    private String dataNascimento;

    private String telefone;

    private EnderecoDTO enderecoDTO;
}
