package br.com.danilopaixao.vehicle.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import br.com.danilopaixao.vehicle.model.Driver;
import br.com.danilopaixao.vehicle.model.Vehicle;

@Service
public class VehicleService {
	
	@Value("${br.com.danilopaixao.service.driver.host}")
	private String hostDriverService;
	
	@Value("${br.com.danilopaixao.service.driver.protocol}")
	private String protocolDriverService;
	
	@Value("${br.com.danilopaixao.service.driver.version}")
	private String versionDriverService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@HystrixCommand(fallbackMethod ="getAllVehicleFallBack",
			threadPoolKey = "getAllVehicleThreadPool",
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "75"),
					@HystrixProperty(name = "maxQueueSize", value = "35")
			},
			commandProperties = {
				@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
				@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
				@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
				@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
			}
	)
	public List<Vehicle> getAllVehicle(){
		Driver driver = restTemplate.getForObject(this.protocolDriverService+"://"+this.hostDriverService+"/api/"+this.versionDriverService+"/drivers/testeAllVehicle", Driver.class);
		List<Vehicle> vehicles = Arrays.asList(
				new Vehicle("VEHICLE NAME TESTE 1", "VEHICLE DESCRIPTION  TESTE 1", 0, driver.getName(), driver.getId()),
				new Vehicle("VEHICLE NAME TESTE 2", "VEHICLE DESCRIPTION  TESTE 2", 0, driver.getName(), driver.getId()),
				new Vehicle("VEHICLE NAME TESTE 3", "VEHICLE DESCRIPTION  TESTE 3", 0, driver.getName(), driver.getId()),
				new Vehicle("VEHICLE NAME TESTE 4", "VEHICLE DESCRIPTION  TESTE 4", 0, driver.getName(), driver.getId())
		);
		return vehicles;
	}
	
	public List<Vehicle> getAllVehicleFallBack(){
		List<Vehicle> vehicles = Arrays.asList(
				new Vehicle("NOT FOUND", "", 0, null, null)
		);
		return vehicles;
	}
	
	@HystrixCommand(fallbackMethod ="getVehicleFallBack",
			threadPoolKey = "getVehicleThreadPool",
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "75"),
					@HystrixProperty(name = "maxQueueSize", value = "35")
			},
			commandProperties = {
				@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
				@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
				@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
				@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
			}
	)
	public Vehicle getVehicle(String vehicleId){
		Driver driver = restTemplate.getForObject(this.protocolDriverService+"://"+this.hostDriverService+"/api/"+this.versionDriverService+"/drivers/"+vehicleId, Driver.class);
		return new Vehicle("VEHICLE NAME", "VEHICLE DESCRIPTION", 0, driver.getName(), "IDDRIVER");
	}
	
	public Vehicle getVehicleFallBack(String vehicleId){
		return new Vehicle("NOT FOUND", "", 0, "", "");
	}
	
}
