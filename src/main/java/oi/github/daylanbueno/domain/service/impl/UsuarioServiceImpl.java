package oi.github.daylanbueno.domain.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    /**
     *
     * @param userName
     * @return  retorna o usuário que busca na dade dados
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String  userName) throws UsernameNotFoundException {
        if(!userName.equals("dbsantos")) {
            throw new UsernameNotFoundException("Usuário não encontrado ");
        }
        // fazendo mock de usuário já que ainda não temos cadastrado na base dados.
        return User
                .builder()
                .username("dbsantos")
                .password(passwordEncoder.encode("123"))
                .roles("USER", "ADMIN")
                .build();
    }
}
