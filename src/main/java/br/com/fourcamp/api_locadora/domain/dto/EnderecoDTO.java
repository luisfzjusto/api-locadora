package br.com.fourcamp.api_locadora.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;

    private String uf;

    private String cep;
}
