package joao.desafio_pickpay.entities.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import joao.desafio_pickpay.entities.Payment;
import joao.desafio_pickpay.entities.dto.PaymentDTO;
import joao.desafio_pickpay.service.PaymentService;

@RestController
@RequestMapping(value ="/payment")
public class PaymentResource {

	@Autowired
	private PaymentService service;
	
	@PostMapping
	public ResponseEntity<Payment> transferencia(@RequestBody PaymentDTO transfer) throws Exception{
		Payment pay = service.transfers(transfer);
		return ResponseEntity.ok().body(pay);
	}
}
