package br.com.fourcamp.api_locadora.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDTO {
    private String marca;
    private String modelo;
    private int ano;
    private String placa;
    private double diaria;
    private String categoria;
    private String status;
}
