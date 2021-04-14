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
            System.out.println(" ** APLICACAO INICIADA ** ");
            Cliente cliente = clienteRepository.save(new Cliente("DAILAN BUENO DOS SANTOS"));
            clienteRepository.save(new Cliente("AMANDA CRISTINA PEREIRA "));
            clienteRepository.save(new Cliente("DILAN BUENO DOS RODRIGUES"));
            clienteRepository.save(new Cliente("MARIA LIMA SILVA"));
            clienteRepository.save(new Cliente("MARCOS"));

            System.out.println(cliente.toString());

            cliente.setNome(cliente.getNome()+" atualizdo!");
            Cliente clienteAtulizado = clienteRepository.save(cliente);
            System.out.println(clienteAtulizado.toString());

            System.out.println("***OBTENDO AMANDA**");
            clienteRepository.obterPorNomeSqlNativo("AMANDA").forEach(System.out::println);

            System.out.println("Existe cliente MARCOS? "+ clienteRepository.existsByNome("MARCOS"));
            System.out.println("Existe cliente EDU? "+ clienteRepository.existsByNome("EDU"));
            System.out.println("*** BUSCANDO TODOS ***");
            clienteRepository.findAll().forEach(System.out::println);

            System.out.println("** DELETANDO MARIA **");
            clienteRepository.deleteById(4);

            System.out.println("** RESULTADO SEM MARIA **");
            clienteRepository.findAll().forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasAppApplication.class, args);
    }
}
