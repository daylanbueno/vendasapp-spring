package oi.github.daylanbueno.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /*
       Esse bean é responsável por criptografa e descriptografa a senha do usuário
       O BCryptPasswordEncoder sempre gera hash diferente.
       Maior seguranda.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
        Esse método é responsável pela autenticação do usuário
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }


     /*
        Esse método é responsável pela autorização do usuário
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
