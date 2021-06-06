package oi.github.daylanbueno.domain.service.impl;

import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.LoginDto;
import oi.github.daylanbueno.domain.entity.Usuario;
import oi.github.daylanbueno.domain.exception.RegraNegocioException;
import oi.github.daylanbueno.domain.exception.SenhaOuLoginInvalidoException;
import oi.github.daylanbueno.domain.repository.UsuarioRepository;
import oi.github.daylanbueno.domain.security.JwtService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    /**
     *
     * @param login
     * @return  retorna o usuário que busca na dade dados
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String  login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                    .findByLogin(login)
                    .orElseThrow(() -> {
                        throw new UsernameNotFoundException("Usuário não encontrado ");
                    });
        String[] roles = usuario.isAdmin()  ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public String login(LoginDto loginDto) {
        if (loginDto  == null) {
            throw new RegraNegocioException("O login não pode ser null");
        }

        Usuario usuario = obterUsuarioPorLogin(loginDto.getLogin());
        validaSenhas(loginDto, usuario);
        return jwtService.gerarToken(usuario);
    }

    private void validaSenhas(LoginDto loginDto, Usuario usuario) {
        boolean isSenhaValida = passwordEncoder.matches(loginDto.getSenha(), usuario.getSenha());
        if (!isSenhaValida) {
            throw new SenhaOuLoginInvalidoException();
        }
    }

    private Usuario obterUsuarioPorLogin(String login) {
        return usuarioRepository.
                findByLogin(login).
                orElseThrow(() -> new SenhaOuLoginInvalidoException());
    }
}
