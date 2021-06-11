package oi.github.daylanbueno.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "LOGIN")
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String login;

    @Column(name = "SENHA")
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;

    @Column(name = "ADMIN")
    private boolean admin;

}
