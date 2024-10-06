package br.com.fourcamp.api_locadora.domain.strategy;

public interface PricingStrategy {
    double calcularPreco(double diaria, int quantidade);
}
