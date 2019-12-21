package br.com.danilopaixao.vehicle.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.danilopaixao.vehicle.model.Vehicle;
import br.com.danilopaixao.vehicle.model.VehicleTrack;
import br.com.danilopaixao.vehicle.service.VehicleService;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleResource {
	
	@Autowired
	private VehicleService service;
	
	private static final Logger logger = LoggerFactory.getLogger(VehicleResource.class);
	
	@GetMapping(value="/init", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Vehicle> init(){
		return service.init();
	}
	
	@GetMapping(value = "/near/{latitude}/{longitude}/{distance}")
	public List<Vehicle> findNearest(@PathVariable("latitude") final double latitude, 
			@PathVariable("longitude") final double longitude, @PathVariable("distance") final double distance) {
		return service.findByGeolocationWithin(latitude, longitude, distance);
	}
	
	@GetMapping(value="/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Vehicle getVehicle(@PathVariable("vin") final String vin){
		logger.info("##VehicleResource#getVehicle: {}", vin);
		Vehicle r = service.getVehicle(vin);
		if(r == null) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "vehicle not found");
		}
		return r;
	}
	
	@PutMapping(value="/{vin}/status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Vehicle updateStatus(@PathVariable("vin") final String vin,
			@RequestBody(required = true) final VehicleTrack vehicleTrack){
		logger.info("##VehicleResource#updateStatus: {} , {}", vin, vehicleTrack);
		Vehicle r = service.updateStatus(vin, vehicleTrack.getStatus(), vehicleTrack.getGeolocation()); 
		if(r == null) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_ACCEPTABLE, "impossible change - vehicle not found");
		}
		return r;
	}
	
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Vehicle insertNewVehicle(@RequestBody(required = true) final Vehicle vehicle) {
		return service.insertVehicle(vehicle);
	}
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Vehicle> findAllVehicles() {
		return service.getAllVehicle();
	}
}