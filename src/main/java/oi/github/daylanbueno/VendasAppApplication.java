package oi.github.daylanbueno;

import oi.github.daylanbueno.domain.entity.Cliente;
import oi.github.daylanbueno.domain.entity.Pedido;
import oi.github.daylanbueno.domain.repository.ClienteRepository;
import oi.github.daylanbueno.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository, @Autowired PedidoRepository pedidoRepository) {
        return args -> {
//            System.out.println(" ** APLICACAO INICIADA ** ");
//            Cliente cliente = clienteRepository.save(new Cliente("DAILAN BUENO DOS SANTOS"));
//            clienteRepository.save(new Cliente("AMANDA CRISTINA PEREIRA "));
//            clienteRepository.save(new Cliente("DILAN BUENO DOS RODRIGUES"));
//            clienteRepository.save(new Cliente("MARIA LIMA SILVA"));
//            clienteRepository.save(new Cliente("MARCOS"));
//
//            System.out.println(cliente.toString());
//
//            cliente.setNome(cliente.getNome()+" atualizdo!");
//            Cliente clienteAtulizado = clienteRepository.save(cliente);
//            System.out.println(clienteAtulizado.toString());
//
//            System.out.println("***OBTENDO AMANDA**");
//            clienteRepository.obterPorNomeSqlNativo("AMANDA").forEach(System.out::println);
//
//            System.out.println("Existe cliente MARCOS? "+ clienteRepository.existsByNome("MARCOS"));
//            System.out.println("Existe cliente EDU? "+ clienteRepository.existsByNome("EDU"));
//            System.out.println("*** BUSCANDO TODOS ***");
//            clienteRepository.findAll().forEach(System.out::println);
//
//            System.out.println("** DELETANDO MARIA **");
//            clienteRepository.deleteById(4);
//
//            System.out.println("** RESULTADO SEM MARIA **");
//            clienteRepository.findAll().forEach(System.out::println);


            Cliente marcos = clienteRepository.save(new Cliente("Marcos da Silva"));

            Pedido pedido = new Pedido(marcos, LocalDate.now(), BigDecimal.valueOf(100));
            pedidoRepository.save(pedido);

            System.out.println("*** RESULTADO DE PEDIDOS ***");
            clienteRepository.findClienteFechPedidos(marcos.getId()).getPedidos().forEach(System.out::println);

            System.out.println("*** RESULTADO DE PEDIDOS DE CLIENTE ***");
            pedidoRepository.findByCliente(marcos).forEach(System.out::println);


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasAppApplication.class, args);
    }
}
