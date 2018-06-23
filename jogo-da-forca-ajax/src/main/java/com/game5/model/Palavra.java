package com.game5.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

import com.game5.model.enums.NivelEnum;


/*
 * @author Emanuel Honorio
 * Classe que mapeia a tabela Palavra na base de dados
 * 
 */

@Entity
public class Palavra implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Insira o nome")
	private String nome;
	
	@ManyToOne
	@NotNull(message = "Selecione o tema")
	private Tema tema;
	
	@OneToMany(mappedBy="palavra")
	private List<Dica> dicas;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Selecione o nível")
	private NivelEnum nivel;
	
	@PrePersist
	public void prePersistPreUpdate() {
		this.nome = this.nome.toLowerCase();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Dica> getDicas() {
		return dicas;
	}

	public void setDicas(List<Dica> dicas) {
		this.dicas = dicas;
	}

	public NivelEnum getNivel() {
		return nivel;
	}

	public void setNivel(NivelEnum nivel) {
		this.nivel = nivel;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	public String getUnderlines() {
		
		if(StringUtils.isEmpty(nome)) return "";
			
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < getNome().length(); i++) {
			sb.append('_');
		}
		
		return sb.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Palavra other = (Palavra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
