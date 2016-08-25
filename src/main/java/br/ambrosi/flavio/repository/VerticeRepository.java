package br.ambrosi.flavio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ambrosi.flavio.model.Vertice;

public interface VerticeRepository extends JpaRepository<Vertice, Long>{
	Vertice findByDescricao(String descricao);
}
