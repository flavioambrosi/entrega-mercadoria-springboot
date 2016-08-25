package br.ambrosi.flavio.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections15.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ambrosi.flavio.MenorCaminho;
import br.ambrosi.flavio.model.Aresta;
import br.ambrosi.flavio.model.Vertice;
import br.ambrosi.flavio.repository.ArestaRepository;
import br.ambrosi.flavio.repository.VerticeRepository;
import br.ambrosi.flavio.repository.exception.MapaCadastradoException;
import br.ambrosi.flavio.repository.exception.VerticeNotFoundExcetion;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

@RestController
@RequestMapping("/servicos")
public class ServicoEntregaMercadoria {

	@Autowired
	ArestaRepository aRepo;

	@Autowired
	VerticeRepository vRepo;

	@RequestMapping(method = RequestMethod.GET)
	public void buscaMenorCaminho() {
		System.out.println("Carregando os produtos");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/adicionaMapa/{origem}/{destino}/{distancia}")
	public void adicionaMapa(@PathVariable("origem") String origem, @PathVariable("destino") String destino, @PathVariable("distancia") Integer distancia) throws MapaCadastradoException {

		// Verifica a existencia do ponto de origem informado
		Vertice verticeOrigem = vRepo.findByDescricao(origem);

		// Verifica a existencia dos pontos origem e destino e caso já exista,
		// retorna um erro.
		if (verticeOrigem != null) {
			for (Aresta aresta : verticeOrigem.getArestas()) {
				if (aresta.getDestino().equals(destino)) {
					throw new MapaCadastradoException(
							"Mapa com origem em " + origem + " e destino em " + destino + " já cadastrado.");
				}
			}
		} else {
			verticeOrigem = new Vertice(origem);
			vRepo.save(verticeOrigem);
		}

		Vertice verticeDestino = vRepo.findByDescricao(destino);
		if (verticeDestino == null) {
			verticeDestino = new Vertice(destino);
			vRepo.save(verticeDestino);
		}

		Aresta aresta = new Aresta(verticeOrigem, verticeDestino);
		aresta.setDistancia(distancia);
		aRepo.save(aresta);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/buscaCaminho/{origem}/{destino}/{autonomia}/{valorCombustivel}")
	public ResponseEntity<MenorCaminho> buscaCaminho(@PathVariable("origem") String origem, @PathVariable("destino") String destino, @PathVariable("autonomia") BigDecimal autonomia, @PathVariable("valorCombustivel")BigDecimal valorCombustivel)
			throws VerticeNotFoundExcetion {

		Vertice verticeOrigem = vRepo.findByDescricao(origem);
		if (verticeOrigem == null) {
			throw new VerticeNotFoundExcetion("Origem não encontrada: " + origem);
		}

		Vertice verticeDestino = vRepo.findByDescricao(destino);
		if (verticeDestino == null) {
			throw new VerticeNotFoundExcetion("Destino não encontrado: " + destino);
		}

		Collection<Vertice> vertices = vRepo.findAll();
		Graph<Vertice, Aresta> g = new SparseMultigraph<Vertice, Aresta>();

		for (Vertice vertice : vertices) {
			for (Aresta aresta : vertice.getArestas()) {
				if (aresta.getOrigem().getDescricao().equals(origem)) {
					verticeOrigem = aresta.getOrigem();
				} else if (aresta.getDestino().getDescricao().equals(destino)) {
					verticeDestino = aresta.getDestino();
				}

				g.addEdge(aresta, aresta.getOrigem(), aresta.getDestino());
			}
		}

		Transformer<Aresta, Integer> wtTransformer = new Transformer<Aresta, Integer>() {
			public Integer transform(Aresta link) {
				return link.getDistancia();
			}
		};
		DijkstraShortestPath<Vertice, Aresta> alg = new DijkstraShortestPath<Vertice, Aresta>(g, wtTransformer);
		List<Aresta> l = alg.getPath(verticeOrigem, verticeDestino);
		Number distanciaTotal = alg.getDistance(verticeOrigem, verticeDestino);

		BigDecimal custoTotal = BigDecimal.valueOf(distanciaTotal.intValue()).divide(autonomia).setScale(2,
				BigDecimal.ROUND_HALF_DOWN);
		custoTotal = custoTotal.multiply(valorCombustivel);

		MenorCaminho caminho = new MenorCaminho();
		caminho.setMenorCaminho(l.toString());
		caminho.setDistanciaRota(distanciaTotal);
		caminho.setCustoRota(custoTotal);
		return new ResponseEntity<MenorCaminho>(caminho, HttpStatus.OK);
	}
}
