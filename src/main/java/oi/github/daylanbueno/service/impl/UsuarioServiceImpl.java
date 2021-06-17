package oi.github.daylanbueno.service.impl;

import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.LoginDto;
import oi.github.daylanbueno.domain.dto.UsuarioDto;
import oi.github.daylanbueno.domain.entity.Usuario;
import oi.github.daylanbueno.exception.RegraNegocioException;
import oi.github.daylanbueno.exception.SenhaOuLoginInvalidoException;
import oi.github.daylanbueno.repository.UsuarioRepository;
import oi.github.daylanbueno.security.JwtService;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;
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
    public UsuarioDto salvar(UsuarioDto usuarioDto) {
        Usuario usuario = modelMapper.map(usuarioDto, Usuario.class);
        validaSeUsuarioJaCadastrado(usuarioDto);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return modelMapper.map(novoUsuario, UsuarioDto.class);
    }

    private void validaSeUsuarioJaCadastrado(UsuarioDto usuarioDto) {
        boolean usuarioExiste = usuarioRepository.
                findByLogin(usuarioDto.getLogin()).isPresent();
        if (usuarioExiste) {
            throw new RegraNegocioException("Já existe um usuário para o login informado");
        }
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
