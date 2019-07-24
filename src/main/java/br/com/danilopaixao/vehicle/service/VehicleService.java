package br.com.danilopaixao.vehicle.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danilopaixao.vehicle.model.Driver;
import br.com.danilopaixao.vehicle.model.Vehicle;
import br.com.danilopaixao.vehicle.model.VehicleSummary;
import br.com.danilopaixao.vehicle.repository.VehicleMongoRepository;
import br.com.danilopaixao.vehicle.repository.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private VehicleSocketService vehicleSocketService;
	
	//@Autowired
	//private VehicleRepository vehicleRepository;
	
	@Autowired
	private VehicleMongoRepository vehicleMongoRepository;
	
	public List<Vehicle> init(){
		// in case of duplicate key, ignore and go ahead
		try{vehicleMongoRepository.insert(new Vehicle("YS2R4X20005399401", "ABC123", "VW GOLF", "OFF", "93418DF0R09QSDF"));}catch(Exception e) {}
		try{vehicleMongoRepository.insert(new Vehicle("VLUR4X20009093588", "DEF456", "VW AMAROK", "OFF", "93418DF0R09QSDF"));}catch(Exception e) {}
		try{vehicleMongoRepository.insert(new Vehicle("VLUR4X20009048066", "GHI789", "FIAT TORO", "OFF", "93418DF0R09QSDF"));}catch(Exception e) {}
		
		try{vehicleMongoRepository.insert(new Vehicle("YS2R4X20005388011", "JKL012", "FORD EDGE", "OFF", "623480520FDF2"));}catch(Exception e) {}
		try{vehicleMongoRepository.insert(new Vehicle("YS2R4X20005387949", "MNO345", "FORD FOCUS", "OFF", "623480520FDF2"));}catch(Exception e) {}
		
		try{vehicleMongoRepository.insert(new Vehicle("YS2R4X20005387765", "PQR678", "VOLVO XC60", "OFF", "7428DFEC8137652"));}catch(Exception e) {}
		try{vehicleMongoRepository.insert(new Vehicle("YS2R4X20005387055", "STU901", "VOLVO XC90 ", "OFF", "7428DFEC8137652"));}catch(Exception e) {}
		
		return getAllVehicle();
		
	}
	
	public Vehicle updateStatus(final String vin, final String status){
		vehicleSocketService.updateStatusWebSocket(vin, status);
		Vehicle vehicle = vehicleMongoRepository.findById(vin).map(v-> {
			v.setStatus(status);
			return v;
		}).get();
		vehicleMongoRepository.save(vehicle);
		return vehicle;
	}
	
	public List<VehicleSummary> getAllVehicleSummary(){
		return this.getAllVehicle().stream()
									.map(vehicle -> {
											Driver driver = driverService.getDriver(vehicle.getDriverId());
											VehicleSummary vehicleSummary = new VehicleSummary(
																	vehicle.getVin(), 
																	vehicle.getRegNumber(), 
																	vehicle.getName(), 
																	driver.getId(), 
																	driver.getName(), 
																	driver.getLicenseCategory(), 
																	driver.getAddress(), 
																	vehicle.getStatus());
											return vehicleSummary;
									}).collect(Collectors.toList());
	}
	
	public VehicleSummary getVehicleSummary(final String vin){
		return this.getVehicleOptional(vin)
						.map(vehicle -> {
							Driver driver = driverService.getDriver(vehicle.getDriverId());
							VehicleSummary vehicleSummary = new VehicleSummary(
									vehicle.getVin(), 
									vehicle.getRegNumber(), 
									vehicle.getName(), 
									driver.getId(), 
									driver.getName(), 
									driver.getLicenseCategory(), 
									driver.getAddress(), 
									vehicle.getStatus());
							return vehicleSummary;
						}).orElse(null);
	}
	
	public List<Vehicle> getAllVehicle(){
		return this.vehicleMongoRepository.findAll();
	}
	
	public Optional<Vehicle> getVehicleOptional(final String vin){
		return Optional.of(this.getVehicle(vin));
	}
	
	public Vehicle getVehicle(final String vin){
		//return this.vehicleRepository.findByVin(vin);
		return vehicleMongoRepository.findById(vin).orElse(null);
	}
	
}
