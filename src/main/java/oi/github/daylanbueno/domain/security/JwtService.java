package oi.github.daylanbueno.domain.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import oi.github.daylanbueno.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    @Value("{security.jwt.expricacao}")
    private String expiracao;

    @Value("{security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario) {
        long expiracaoValue = Long.valueOf(expiracao);
        // pega hora atual e adiciona os minutos.
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expiracaoValue);

        // pega horaExpiracao com a zona do sistema converte e instante
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        // caso queira mais informações no seu token.
//        HashMap<String, Object>  cleams  = new HashMap<>();
//        cleams.put("email", "usuario@gmail.com");
//        cleams.put("roles", "admin");

        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
//                .setClaims(cleams) // se quiser mais informações no seu token.
                .signWith(SignatureAlgorithm.ES512, chaveAssinatura)
                .compact();
    }
}
