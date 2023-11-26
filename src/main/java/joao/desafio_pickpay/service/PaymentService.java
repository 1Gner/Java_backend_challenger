package joao.desafio_pickpay.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@Autowired
	private UserService userService;
	@Autowired
	private notificationService noService;
	
	@Autowired(required=false)
	private final RestTemplate resttemplate = new RestTemplate();
	
	
	public Payment transfers(PaymentDTO transferencia) throws Exception {
		
		User pagador = userService.findById(transferencia.getId1());
		User recebedor = userService.findById(transferencia.getId2());
		
		validacao(pagador,transferencia.getValor());
		boolean Autorizacao = autorizador();
		if(Autorizacao == false) {
			throw new Exception("Nao autorizada");
		}
		
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
		if(user.getTipo().getCode() == 1 ) {
			
			throw new Exception("Lojistas nao pode transferir");
		}
		
		if(user.getMoney() < valor ) {
			throw new Exception("Voce nao tem dinheiro suficiente");
		}
	}
	
	public boolean autorizador() {
		var response = resttemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc"
				,Map.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			String message = (String)response.getBody().get("message");
			return "Autorizado".equalsIgnoreCase(message);
		}else {
			return false;
		}
	}
	
}
