package oi.github.daylanbueno.config;

import oi.github.daylanbueno.anotation.Desenvolvimento;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Desenvolvimento
public class ConfiguracaoDevenvolvimento {

    @Bean
    public CommandLineRunner inicio() {
        return args -> {
            System.out.println("aplicação iniciada no ambinente de desenvolvimento");
        };
    }
}
