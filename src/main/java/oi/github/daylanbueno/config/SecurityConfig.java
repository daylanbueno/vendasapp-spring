package oi.github.daylanbueno.config;

import oi.github.daylanbueno.security.JwtAuthFilter;
import oi.github.daylanbueno.security.JwtService;
import oi.github.daylanbueno.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*
     busca a implementação, que busca o usuário na base de dados.
     */
    @Autowired
    private  UsuarioServiceImpl usuarioService;

    @Autowired
    private JwtService jwtService;

    /*
       Esse bean é responsável por criptografa e descriptografa a senha do usuário
       O BCryptPasswordEncoder sempre gera hash diferente.
       Maior seguranda.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(usuarioService, jwtService);
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
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests()
            .antMatchers("/api/clientes/**")
                .hasAnyRole("USER","ADMIN")
            .antMatchers("/api/pedidos/**")
                .hasAnyRole("USER","ADMIN")
           .antMatchers("/api/produtos")
                .hasAnyRole("ADMIN")
            .antMatchers(HttpMethod.POST,"/api/usuarios/**")
                .permitAll()
            .antMatchers("/h2-console/**")
                .permitAll()
           .anyRequest().authenticated()
           .and()
            .sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS)
           .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
