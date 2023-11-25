package joao.desafio_pickpay.entities.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import joao.desafio_pickpay.entities.User;
import joao.desafio_pickpay.service.UserComumService;

@RestController
@RequestMapping(value ="/users")
public class UserComumResource {
	
	@Autowired
	public UserComumService service;
	
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User user){
		user = service.insert(user);
		return ResponseEntity.ok().body(user);
	}
	
	
}
