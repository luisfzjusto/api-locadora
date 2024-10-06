package br.com.fourcamp.api_locadora.domain.strategy;

public class MensalStrategy implements PricingStrategy{

    @Override
    public double calcularPreco(double diaria, int quantidade){
        return ((diaria * 30) * 0.85) * quantidade;
    }
}
