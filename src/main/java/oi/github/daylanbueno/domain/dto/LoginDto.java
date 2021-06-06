package oi.github.daylanbueno.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String login;
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;
}
