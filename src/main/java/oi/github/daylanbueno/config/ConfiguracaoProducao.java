package oi.github.daylanbueno.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ConfiguracaoProducao {

    @Bean
    public CommandLineRunner execucao() {
        return args -> {
            System.out.println("aplicação iniciada em produção.");
        };
    }

}
