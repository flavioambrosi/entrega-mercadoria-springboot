package br.ambrosi.flavio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Classe que representa um vertice no grafo.
 * @author Flavio
 *
 */
@Entity
@Table(name="VERTICE")
public class Vertice  {

	/**
	 * Identificador interno
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Descricao do vertice
	 */
	@Column(name="DESCRICAO")
	private String descricao;

	/**
	 * Arestas do vertice
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="FK_VERTICE_ORIGEM")
	private List<Aresta> arestas = new ArrayList<Aresta>();

	public Vertice() {

	}

	public Vertice(String descricao) {
		this.setDescricao(descricao);
	}

	public void setDescricao(String nome) {
		this.descricao = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public void setArestas(List<Aresta> arestas) {
		this.arestas = arestas;
	}

	@Override
	public String toString() {
		String s = " ";
		s += this.getDescricao();
		return s;
	}

	@Override
	public boolean equals(Object obj) {
	    if(!(obj instanceof Vertice)){
	        return false;
	    }

	    EqualsBuilder builder = new EqualsBuilder();
	    Vertice other = (Vertice) obj;
	    builder.append(this.id, other.id);
	    builder.append(this.descricao, other.descricao);

	    return builder.isEquals();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
	    HashCodeBuilder builder = new HashCodeBuilder(1,31);
	    builder.append(this.id);
	    builder.append(this.descricao);

	    return builder.toHashCode();
	}
}
