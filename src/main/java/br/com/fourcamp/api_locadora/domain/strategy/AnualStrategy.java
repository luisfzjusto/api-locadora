package br.com.fourcamp.api_locadora.domain.strategy;

public class AnualStrategy implements PricingStrategy {
    @Override
    public double calcularPreco(double diaria, int quantidade){
        return ((diaria * 365) * 0.75) * quantidade;
    }
}
