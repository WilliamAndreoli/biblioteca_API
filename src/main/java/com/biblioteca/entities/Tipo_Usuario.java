package com.biblioteca.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tipo_Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	private String descricao;
	private Integer dias_emprestimo;
	private Double multa_diaria;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getDias_emprestimo() {
		return dias_emprestimo;
	}
	
	public void setDias_emprestimo(Integer dias_emprestimo) {
		this.dias_emprestimo = dias_emprestimo;
	}
	
	public Double getMulta_diaria() {
		return multa_diaria;
	}
	
	public void setMulta_diaria(Double multa_diaria) {
		this.multa_diaria = multa_diaria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tipo_Usuario other = (Tipo_Usuario) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
