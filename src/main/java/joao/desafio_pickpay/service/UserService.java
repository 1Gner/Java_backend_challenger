package joao.desafio_pickpay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.curso.service.exceptions.ResourceNotFoundException;

import joao.desafio_pickpay.entities.User;
import joao.desafio_pickpay.entities.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	
	public List<User>findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User user) {
		repository.save(user);
		return user;
	}
	
	public void saveUser(User user){
		user = repository.save(user);
	}
	
}
