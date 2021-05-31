package oi.github.daylanbueno.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUTO")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao", length = 100)
    @NotEmpty(message = "O campo descricao é obrigatório")
    private String descricao;

    @NotNull(message = "O campo preço é obrigatório")
    @Column(name = "preco_unitario")
    private BigDecimal preco;
}


