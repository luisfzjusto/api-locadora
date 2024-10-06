package br.com.fourcamp.api_locadora.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaLocadora {
    private Long id = 1L;
    private double saldo;

    public void adicionarSaldo(double valor){
        this.saldo += valor;
    }
}
