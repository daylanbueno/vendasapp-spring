package oi.github.daylanbueno;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

    public static void main(String[] args) {
        SpringApplication.run(VendasAppApplication.class, args);
    }
}
