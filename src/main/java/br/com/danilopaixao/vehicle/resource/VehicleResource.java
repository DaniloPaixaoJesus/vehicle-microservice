package br.com.danilopaixao.vehicle.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danilopaixao.vehicle.model.Vehicle;
import br.com.danilopaixao.vehicle.service.VehicleService;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleResource {
	
	@Autowired
	private VehicleService service;
	
	@RequestMapping("")
	public List<Vehicle> getAllVehicle(){
		return service.getAllVehicle();
	}
	
	@RequestMapping("/{id}")
	public Vehicle getVehicle(@PathVariable("id") String id){
		return service.getVehicle(id);
	}
}


//@Autowired
//WebClient.Builder builderWebClient;

//Movie movietest = builderWebClient.build()
//		.get()
//		.uri("http://driver/movies/foo")
//		.retrieve()
//		.bodyToMono(Movie.class)
//		.block();
