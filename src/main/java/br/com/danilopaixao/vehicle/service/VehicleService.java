package br.com.danilopaixao.vehicle.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import br.com.danilopaixao.vehicle.enums.StatusEnum;
import br.com.danilopaixao.vehicle.model.Location;
import br.com.danilopaixao.vehicle.model.Vehicle;
import br.com.danilopaixao.vehicle.repository.VehicleMongoRepository;

@Service
public class VehicleService {
	
	private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);
	
	@Autowired
	private VehicleMongoRepository vehicleMongoRepository;
	
	public List<Vehicle> init(){
		// in case of duplicate key, ignore and go ahead
		try{saveVehicle(new Vehicle("YS2R4X20005399401", "ABC123", "VW GOLF", StatusEnum.OFF));}catch(Exception e) {}
		try{saveVehicle(new Vehicle("VLUR4X20009093588", "DEF456", "VW AMAROK", StatusEnum.OFF));}catch(Exception e) {}
		try{saveVehicle(new Vehicle("VLUR4X20009048066", "GHI789", "FIAT TORO", StatusEnum.OFF));}catch(Exception e) {}
		
		try{saveVehicle(new Vehicle("YS2R4X20005388011", "JKL012", "FORD EDGE", StatusEnum.OFF));}catch(Exception e) {}
		try{saveVehicle(new Vehicle("YS2R4X20005387949", "MNO345", "FORD FOCUS", StatusEnum.OFF));}catch(Exception e) {}
		
		try{saveVehicle(new Vehicle("YS2R4X20005387765", "PQR678", "VOLVO XC60", StatusEnum.OFF));}catch(Exception e) {}
		try{saveVehicle(new Vehicle("YS2R4X20005387055", "STU901", "VOLVO XC90 ", StatusEnum.OFF));}catch(Exception e) {}
		
		return getAllVehicle();
		
	}
	
	public Vehicle insertVehicle(Vehicle vehicle) {
		return vehicleMongoRepository.insert(vehicle);
	}
	
	public Vehicle saveVehicle(Vehicle vehicle) {
		return vehicleMongoRepository.save(vehicle);
	}
	
	public Vehicle updateStatus(final String vin, final StatusEnum status, Location location){
		logger.info("##VehicleService#updateStatus vin {}, status {}, location {}", vin, status, location);
		return vehicleMongoRepository.findById(vin)
								.map(v-> {
									v.setStatus(status);
									v.setGeolocation(location);
									vehicleMongoRepository.save(v);
									return v;
								}).orElseGet(()-> null);
	}
	
	public Vehicle updateStatus(final String vin, final StatusEnum status){
		logger.info("##VehicleService#updateStatus vin {}, status {}", vin, status);
		return vehicleMongoRepository.findById(vin)
								.map(v-> {
									v.setStatus(status);
									vehicleMongoRepository.save(v);
									return v;
								}).orElseGet(()-> null);
	}
	
	public List<Vehicle> findByGeolocationWithin(double latitude, double longitude, double distance) {
		logger.info("##VehicleService#findByGeolocationWithin: {} ; {} ; {}", latitude, longitude, distance);
		Point point = new Point(latitude, longitude);
		Distance radious = new Distance(distance, Metrics.KILOMETERS);
		Circle circle = new Circle(point, radious);
		return this.vehicleMongoRepository.findByGeolocationWithin(circle);
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
