package joao.desafio_pickpay.service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import joao.desafio_pickpay.entities.Payment;
import joao.desafio_pickpay.entities.User;
import joao.desafio_pickpay.entities.dto.PaymentDTO;
import joao.desafio_pickpay.entities.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repository;
	

	private RestTemplate restTemplate;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private notificationService noService;
	
	
	public Payment transfers(PaymentDTO transferencia) throws Exception {
		
		User pagador = userService.findById(transferencia.getId1());
		User recebedor = userService.findById(transferencia.getId2());
		
		validacao(pagador,transferencia.getValor());
		/*boolean Autorizacao = autorizador();
		if(!Autorizacao) {
			throw new Exception("Nao autorizada");
		}*/
		
		Payment trans = new Payment();
		trans.setUser1(pagador);
		trans.setUser2(recebedor);
		trans.setTransactionTime(LocalDateTime.now());
		trans.setValor(transferencia.getValor());
		
		
		pagador.troca_perde(transferencia.getValor());
		recebedor.troca_ganha(transferencia.getValor());
		
		repository.save(trans);
		
		this.userService.saveUser(pagador);
		this.userService.saveUser(recebedor);
		
		noService.sendNotificatrion(pagador, "Transacao realizada ");
		noService.sendNotificatrion(recebedor, "Transacao recebida ");
		
		repository.save(trans);
		
		return  trans;
	}
	
	public void validacao(User user, Double valor) throws Exception {				
		if(user.getTipo().getCode() == 2 ) {
			
			throw new Exception("Lojistas nao pode transferir");
		}
		
		if(user.getMoney() < valor ) {
			throw new Exception("Voce nao tem dinheiro suficiente");
		}
	}
	
	/*public boolean autorizador() {
		ResponseEntity<Map>response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc"
				,Map.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			//String message =  (String) response.getBody().getString(message);
			return true;
		}else {
			return false;
		}
	}*/
	
}
