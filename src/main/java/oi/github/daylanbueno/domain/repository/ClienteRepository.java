package oi.github.daylanbueno.domain.repository;

import oi.github.daylanbueno.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?) ";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE";
    private static  String UPDATE = "UPDATE CLIENTE SET NOME =? WHERE ID = ?";
    private static String DELETE = "DELETE FROM CLIENTE WHERE ID = ? ";
    private static String SELECT_POR_NOME = "SELECT * FROM WHERE NOME  LIKE ? ";

    public Cliente salva (Cliente cliente) {
        jdbcTemplate.update(INSERT, new  Object[] {cliente.getNome()});
        return cliente;
    }

    public Cliente atualiza(Cliente cliente) {
        jdbcTemplate.update(UPDATE,
                new Object[]{
                        cliente.getNome(),cliente.getId()
                });
        return cliente;
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public List<Cliente> obterPorNome(String nome) {
        return jdbcTemplate.query(
                SELECT_ALL.concat(" where  nome like ? "),
                new Object[]{"%"+nome+"%"}
                ,obterClienteMapper());
    }

    public List<Cliente> obterTodos() {
        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
    }

    private RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Cliente(resultSet.getInt("id"), resultSet.getString("nome"));
            }
        };
    }
}
