package br.com.danilopaixao.vehicle.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.danilopaixao.vehicle.enums.StatusEnum;
import br.com.danilopaixao.vehicle.model.Vehicle;
import br.com.danilopaixao.vehicle.model.VehicleSummary;
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
	
	@GetMapping(value="", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<VehicleSummary> getAllVehicle(){
		logger.info("##VehicleResource#getAllVehicle: no argument");
		return service.getAllVehicleSummary();
	}
	
	@GetMapping(value="/{vin}/summary", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody VehicleSummary getVehicleSummary(@PathVariable("vin") final String vin){
		logger.info("##VehicleResource#getVehicleSummary: {}", vin);
		return service.getVehicleSummary(vin);
	}
	
	@GetMapping(value="/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Vehicle getVehicle(@PathVariable("vin") final String vin){
		logger.info("##VehicleResource#getVehicle: {}", vin);
		return service.getVehicle(vin);
	}
	
	@PutMapping(value="/{vin}/status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Vehicle updateStatus(@PathVariable("vin") final String vin,
			@RequestBody(required = true) final StatusEnum status){
		logger.info("##VehicleResource#updateStatus: {} , {}", vin, status);
		return service.updateStatus(vin, status);
	}
}