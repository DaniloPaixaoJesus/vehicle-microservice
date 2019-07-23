package br.com.danilopaixao.vehicle.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danilopaixao.vehicle.model.Driver;
import br.com.danilopaixao.vehicle.model.Vehicle;
import br.com.danilopaixao.vehicle.model.VehicleSummary;

@Service
public class VehicleService {
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	VehicleSocketService vehicleSocketService;
	
	private static List<Vehicle> vehiclesMock = Arrays.asList(
			new Vehicle("YS2R4X20005399401", "ABC123", "VW GOLF", "OFF", "93418DF0R09QSDF"),
			new Vehicle("VLUR4X20009093588", "DEF456", "VW AMAROK", "OFF", "93418DF0R09QSDF"),
			new Vehicle("VLUR4X20009048066", "GHI789", "FIAT TORO", "OFF", "93418DF0R09QSDF"),
			
			new Vehicle("YS2R4X20005388011", "JKL012", "FORD EDGE", "OFF", "623480520FDF2"),
			new Vehicle("YS2R4X20005387949", "MNO345", "FORD FOCUS", "OFF", "623480520FDF2"),
			
			new Vehicle("YS2R4X20005387765", "PQR678", "VOLVO XC60", "OFF", "93418DF0R09QSDF"),
			new Vehicle("YS2R4X20005387055", "STU901", "VOLVO XC90 ", "OFF", "93418DF0R09QSDF")
	);
	
	
	public Vehicle updateStatus(final String vin, final String status){
		vehicleSocketService.updateStatusWebSocket(vin, status);
		return this.updateStatusFromRepository(vin, status)
							.map(v -> this.getVehicleFromRepository(vin))
							.orElse(null);
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
		return this.getAllVehicleFromRepository();
	}
	
	public Optional<Vehicle> getVehicleOptional(final String vin){
		return Optional.of(this.getVehicleFromRepository(vin));
	}
	
	public Vehicle getVehicle(final String vin){
		return this.getVehicleFromRepository(vin);
	}
	
	
	/**
	 * TODO: Metodos serao substituidos depois que fizer a classe Repository
	 * 
	 */
	
	private Vehicle getVehicleFromRepository(final String vin){
		/**
		 * TODO: code repository and mongo db access
		 */
		Vehicle vehicle = this.getAllVehicleFromRepository().stream()
								.filter(v -> v.getVin().equalsIgnoreCase(vin))
								.findFirst()
								.orElse(null);
		return vehicle;
	}
	
	private Optional<Vehicle> updateStatusFromRepository(final String vin, final String status){
		/**
		 * TODO: code repository and mongo db access
		 * 		 this method goes to database and execute a update
		 */
		for (Vehicle vehicle : vehiclesMock) {
			if(vehicle.getVin().equalsIgnoreCase(vin)) {
				vehicle.setStatus(status);
			}
		}
		return Optional.of(this.getVehicleFromRepository(vin));
	}
	
	private List<Vehicle> getAllVehicleFromRepository(){
		/**
		 * TODO: code repository and mongo db access
		 */
		return vehiclesMock;
	}
	
}
