package br.com.danilopaixao.vehicle.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danilopaixao.vehicle.model.Driver;
import br.com.danilopaixao.vehicle.model.Vehicle;
import br.com.danilopaixao.vehicle.model.VehicleSummary;
import br.com.danilopaixao.vehicle.repository.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private VehicleSocketService vehicleSocketService;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public Vehicle updateStatus(final String vin, final String status){
		vehicleSocketService.updateStatusWebSocket(vin, status);
		return this.vehicleRepository.updateStatus(vin, status);
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
		return this.vehicleRepository.findAll();
	}
	
	public Optional<Vehicle> getVehicleOptional(final String vin){
		return Optional.of(this.getVehicle(vin));
	}
	
	public Vehicle getVehicle(final String vin){
		return this.vehicleRepository.findByVin(vin);
	}
	
}
