package br.com.fourcamp.api_locadora.domain.config;

import br.com.fourcamp.api_locadora.domain.strategy.AnualStrategy;
import br.com.fourcamp.api_locadora.domain.strategy.DiariaStrategy;
import br.com.fourcamp.api_locadora.domain.strategy.MensalStrategy;
import br.com.fourcamp.api_locadora.domain.strategy.PricingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PricingStrategyConfig {

    @Bean
    @Primary
    public PricingStrategy diariaStrategy(){
        return new DiariaStrategy();
    }

    @Bean
    public PricingStrategy mensalStrategy(){
        return new MensalStrategy();
    }

    @Bean
    public PricingStrategy anualStrategy(){
        return new AnualStrategy();
    }
}
