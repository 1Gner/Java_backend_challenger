package joao.desafio_pickpay.service;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import joao.desafio_pickpay.entities.Payment;
import joao.desafio_pickpay.entities.User;
import joao.desafio_pickpay.entities.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public Payment transfer(Payment transferencia) throws Exception {
		
		validacao(transferencia.getUser1(),transferencia.getValor());
		boolean Autorizacao = autorizador();
		if(!Autorizacao) {
			throw new Exception("Nao autorizada");
		}
	}
	
	public void validacao(User user, Double valor) throws Exception {				
		if(user.getTipo().getCode() == 2 ) {
			
			throw new Exception("Lojistas nao pode transferir");
		}
		
		if(user.getMoney() < valor ) {
			throw new Exception("Voce nao tem dinheiro suficiente");
		}
	}
	
	public boolean autorizador() {
		var response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc"
				,Map.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			String message = response.getBody().get("message");
			return "Autorizado".equalsIgnoreCase(message);
		}else {
			return false;
		}
	}
	
}
