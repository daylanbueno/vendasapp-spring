package oi.github.daylanbueno.domain.config;

import oi.github.daylanbueno.domain.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
     busca a implementação, que busca o usuário na base de dados.
     */
    @Autowired
    private  UsuarioServiceImpl usuarioService;

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
        auth
            .userDetailsService(usuarioService) // vai busca a implementação, para carregar usuário do banco de dados.
            .passwordEncoder(passwordEncoder());
    }
     /*
        Esse método é responsável pela autorização do usuário
        quem acessa quem?
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/clientes/**")
                .hasAnyRole("USER","ADMIN")
            .antMatchers("/api/pedidos/**")
                .hasAnyRole("USER","ADMIN")
           .antMatchers("/api/produtos")
                .hasAnyRole("ADMIN")
                .and()
            .httpBasic();
    }
}
