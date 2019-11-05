package br.com.danilopaixao.vehicle.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danilopaixao.vehicle.enums.StatusEnum;
import br.com.danilopaixao.vehicle.model.Driver;
import br.com.danilopaixao.vehicle.model.Vehicle;
import br.com.danilopaixao.vehicle.model.VehicleSummary;
import br.com.danilopaixao.vehicle.repository.VehicleMongoRepository;

@Service
public class VehicleService {
	
	private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private VehicleSocketService vehicleSocketService;
	
	@Autowired
	private VehicleMongoRepository vehicleMongoRepository;
	
	public List<Vehicle> init(){
		// in case of duplicate key, ignore and go ahead
		try{vehicleMongoRepository.insert(new Vehicle("YS2R4X20005399401", "ABC123", "VW GOLF", StatusEnum.OFF, "93418DF0R09QSDF"));}catch(Exception e) {}
		try{vehicleMongoRepository.insert(new Vehicle("VLUR4X20009093588", "DEF456", "VW AMAROK", StatusEnum.OFF, "93418DF0R09QSDF"));}catch(Exception e) {}
		try{vehicleMongoRepository.insert(new Vehicle("VLUR4X20009048066", "GHI789", "FIAT TORO", StatusEnum.OFF, "93418DF0R09QSDF"));}catch(Exception e) {}
		
		try{vehicleMongoRepository.insert(new Vehicle("YS2R4X20005388011", "JKL012", "FORD EDGE", StatusEnum.OFF, "623480520FDF2"));}catch(Exception e) {}
		try{vehicleMongoRepository.insert(new Vehicle("YS2R4X20005387949", "MNO345", "FORD FOCUS", StatusEnum.OFF, "623480520FDF2"));}catch(Exception e) {}
		
		try{vehicleMongoRepository.insert(new Vehicle("YS2R4X20005387765", "PQR678", "VOLVO XC60", StatusEnum.OFF, "7428DFEC8137652"));}catch(Exception e) {}
		try{vehicleMongoRepository.insert(new Vehicle("YS2R4X20005387055", "STU901", "VOLVO XC90 ", StatusEnum.OFF, "7428DFEC8137652"));}catch(Exception e) {}
		
		return getAllVehicle();
		
	}
	
	public Vehicle updateStatus(final String vin, final StatusEnum status){
		logger.info("##VehicleService#updateStatus vin {}, status {}", vin, status);
		vehicleSocketService.updateStatusWebSocket(vin, status);
		return vehicleMongoRepository.findById(vin)
								.map(v-> {
									v.setStatus(status);
									vehicleMongoRepository.save(v);
									return v;
								}).orElseGet(()-> null);
	}
	
	public List<VehicleSummary> getAllVehicleSummary(){
		logger.info("##VehicleService#getAllVehicleSummary: no argument");
		return this.getAllVehicle().stream()
									.map(vehicle -> {
											Driver driver = driverService.getDriver(vehicle.getDriverId());
											return new VehicleSummary(
																	vehicle.getVin(), 
																	vehicle.getRegNumber(), 
																	vehicle.getName(), 
																	driver.getId(), 
																	driver.getName(), 
																	driver.getLicenseCategory(), 
																	driver.getAddress(), 
																	vehicle.getStatus());
									}).collect(Collectors.toList());
	}
	
	public VehicleSummary getVehicleSummary(final String vin){
		logger.info("##VehicleService#getVehicleSummary vin {}", vin);
		return this.getVehicleOptional(vin)
						.map(vehicle -> {
							Driver driver = driverService.getDriver(vehicle.getDriverId());
							return new VehicleSummary(
									vehicle.getVin(), 
									vehicle.getRegNumber(), 
									vehicle.getName(), 
									driver.getId(), 
									driver.getName(), 
									driver.getLicenseCategory(), 
									driver.getAddress(), 
									vehicle.getStatus());
						}).orElse(null);
	}
	
	public List<Vehicle> getAllVehicle(){
		logger.info("##VehicleService#getAllVehicle: no argument");
		return this.vehicleMongoRepository.findAll();
	}
	
	public Optional<Vehicle> getVehicleOptional(final String vin){
		logger.info("##VehicleService#getVehicleOptional vin {}", vin);
		return Optional.of(this.getVehicle(vin));
	}
	
	public Vehicle getVehicle(final String vin){
		logger.info("##VehicleService#getVehicle vin {}", vin);
		return vehicleMongoRepository.findById(vin).orElse(null);
	}
	
}
