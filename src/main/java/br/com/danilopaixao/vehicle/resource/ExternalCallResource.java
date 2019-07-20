package br.com.danilopaixao.vehicle.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/teste-chamada-externa")
public class ExternalCallResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@RequestMapping("/{id}")
//	public ResponseEntity<User> getHttpMethod(@PathVariable("id") String id){
//		ResponseEntity<User> user = restTemplate.postForEntity("http://34.229.135.40:8080/init/users", null, User.class);
//		return user;
//	}
	
}
