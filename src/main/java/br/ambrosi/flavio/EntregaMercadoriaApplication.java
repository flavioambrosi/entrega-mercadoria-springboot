package br.ambrosi.flavio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories("br.ambrosi.flavio.repository")
public class EntregaMercadoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntregaMercadoriaApplication.class, args);
	}
}
