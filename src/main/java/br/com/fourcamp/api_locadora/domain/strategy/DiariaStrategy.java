package br.com.fourcamp.api_locadora.domain.strategy;

public class DiariaStrategy implements PricingStrategy{

    @Override
    public double calcularPreco(double diaria, int quantidade){
        return diaria * quantidade;
    }
}
