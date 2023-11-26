package joao.desafio_pickpay.entities.dto;

import java.io.Serializable;



public class PaymentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Long Id1;
	
	
	private Long Id2;
	
	private Double valor;
	

	
	public PaymentDTO() {
		
	}



	public PaymentDTO(Long id1, Long id2, Double valor) {
		super();
		this.Id1 = id1;
		this.Id2 = id2;
		this.valor = valor;
	}



	public Long getId1() {
		return Id1;
	}



	public void setId1(Long id1) {
		Id1 = id1;
	}



	public Long getId2() {
		return Id2;
	}



	public void setId2(Long id2) {
		Id2 = id2;
	}



	public Double getValor() {
		return valor;
	}



	public void setValor(Double valor) {
		this.valor = valor;
	}

	
	
}
