package com.biblioteca.entities;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date data_emprestimo;
	
	private Date data_previsao;
	
	private Date data_entrega;
	
	private Double multa;
	
	@ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
	private Livro livro;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
	 @JsonIgnoreProperties({"senha", "email", "nome"}) // ou outras propriedades que você não quer atualizar
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData_emprestimo() {
		return data_emprestimo;
	}

	public void setData_emprestimo(Date data_emprestimo) {
		this.data_emprestimo = data_emprestimo;
	}

	public Date getData_previsao() {
		return data_previsao;
	}

	public void setData_previsao(Date data_previsao) {
		this.data_previsao = data_previsao;
	}

	public Date getData_entrega() {
		return data_entrega;
	}

	public void setData_entrega(Date data_entrega) {
		this.data_entrega = data_entrega;
	}

	public Double getMulta() {
		calcularMulta();
		return multa;
	}

	public void setMulta(Double multa) {
		this.multa = multa;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void calcularMulta() {
        if (data_entrega != null && data_entrega.after(data_previsao)) {
            long diffInMillies = Math.abs(data_entrega.getTime() - data_previsao.getTime());
            long diasAtraso = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            
           
            // Obter a multa diária do tipo de usuário
            Double multaDiaria = usuario.getTipo_usuario().getMulta_diaria();
            
            // Calcular a multa total
            this.multa = diasAtraso * multaDiaria;
        } else {
            this.multa = 0.0;
        }
    }
	
	public Double calcularMultaAlteraEmprestimo(Date data_entrega, Date data_previsao, Tipo_Usuario tipoUsuario) {
        if (data_entrega != null && data_entrega.after(data_previsao)) {
            long diffInMillies = Math.abs(data_entrega.getTime() - data_previsao.getTime());
            long diasAtraso = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            
           
            // Obter a multa diária do tipo de usuário
            Double multaDiaria = tipoUsuario.getMulta_diaria();
            
            // Calcular a multa total
            return diasAtraso * multaDiaria;
        } else {
            return 0.0;
        }
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
		Emprestimo other = (Emprestimo) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}