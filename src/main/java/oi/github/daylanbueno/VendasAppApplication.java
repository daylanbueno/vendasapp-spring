package oi.github.daylanbueno;

import oi.github.daylanbueno.domain.entity.Cliente;
import oi.github.daylanbueno.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasAppApplication {

    @Value("${applicationName}")
    private String applicationName;

    @GetMapping("/hello")
    public String helloWorld() {
        return applicationName;
    }

    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {
        return args -> {
            clienteRepository.salva(new Cliente("Dailan Bueno"));
            clienteRepository.salva(new Cliente("Amanda Cristina"));
            clienteRepository.salva(new Cliente("Dilan Bueno Rodrigues"));

            List<Cliente> clientes = clienteRepository.obterTodos();
            clientes.forEach(System.out::println);

            clientes.forEach( c -> {
                c.setNome(c.getNome() + " atualizado");
                clienteRepository.atualiza(c);
            });
            System.out.println("clientes atualziados");
            clientes.forEach(System.out::println);

            System.out.println("recuperando amanda");
            clienteRepository.obterPorNome("Amanda").forEach(System.out::println);

            System.out.println("clientes deletando por id");
            clienteRepository.deletar(1);
            System.out.println("NOVA LISTA SEM O CLIENTE 1");
            clienteRepository.obterTodos().forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasAppApplication.class, args);
    }
}
