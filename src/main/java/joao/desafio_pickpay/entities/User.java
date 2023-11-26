package joao.desafio_pickpay.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import joao.desafio_pickpay.entities.enums.Tipo;




@Entity
@Table(name = "tb_user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String nome;
	
	@Column(unique= true)
	private String cpf;
	@Column(unique= true)
	private String email;
	
	private String senha;
	
	private Double money;
	
	private Integer tipo;
	
	
	
	public User() {
		
	}

	public User(Long id, String nome, String cpf, String email, String senha,Double money,Tipo tipo) {
		Id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.money = money;
		setTipo(tipo);
	}



	public Long getId() {
		return Id;
	}



	public void setId(Long id) {
		Id = id;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	public Double getMoney() {
		return money;
	}



	public void setMoney(Double money) {
		this.money = money;
	}

	
	
	public Tipo getTipo() {
		return Tipo.valueOff(tipo);
	}

	public void setTipo(Tipo tipo) {
		if(tipo != null) {
			this.tipo = tipo.getCode();
		}
		
	}
	public void troca_perde(Double valor) {
		this.money -= valor;
	}
	public void troca_ganha(Double valor) {
		this.money += valor;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(Id, other.Id);
	}

}
