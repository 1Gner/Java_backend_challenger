package joao.desafio_pickpay.service;

import org.springframework.stereotype.Service;

import joao.desafio_pickpay.entities.User;

@Service
public class notificationService {
	
	
	public void sendNotificatrion(User user, String message) {
		String email = user.getEmail();
		
		System.out.println(email + message);
	}
}
