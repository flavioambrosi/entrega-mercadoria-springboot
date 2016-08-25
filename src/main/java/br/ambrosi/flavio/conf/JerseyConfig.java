package br.ambrosi.flavio.conf;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import br.ambrosi.flavio.api.ServicoEntregaMercadoria;


@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(ServicoEntregaMercadoria.class);
	}

}