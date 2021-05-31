package oi.github.daylanbueno.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @NotEmpty(message = "O campo nome é obrigatório")
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 15)
    @NotEmpty(message = "O campo CPF é obrigatório")
    @CPF(message = "O informe um CPF válido.")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;
}
