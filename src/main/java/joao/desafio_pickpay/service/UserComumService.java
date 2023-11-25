package joao.desafio_pickpay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import joao.desafio_pickpay.entities.User;
import joao.desafio_pickpay.entities.repository.UserComumRepository;

@Service
public class UserComumService {
	
	@Autowired
	private UserComumRepository repository;
	
	
	public List<User>findAll(){
		return repository.findAll();
	}
	
	public Optional<User> findById(Long id) {
		
		return this.repository.findById(id);
	}
	
	public User insert(User user) {
		user = repository.save(user);
		return user;
	}
	
}
