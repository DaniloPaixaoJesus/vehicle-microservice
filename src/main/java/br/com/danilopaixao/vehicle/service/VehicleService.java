package br.com.danilopaixao.vehicle.service;

import java.time.LocalDateTime;
import java.util.List;

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
import br.com.danilopaixao.vehicle.model.VehicleList;
import br.com.danilopaixao.vehicle.repository.VehicleMongoRepository;
import br.com.danilopaixao.vehicle.repository.VehicleMongoRepository2;

@Service
public class VehicleService {
	
	private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);
	
	@Autowired
	private VehicleMongoRepository vehicleMongoRepository;
	
	@Autowired
	private VehicleMongoRepository2 vehicleMongoRepository2;
	
	public VehicleList init(){
		// in case of duplicate key, ignore and go ahead
		try{saveVehicle(new Vehicle("YS2R4X20005399401", "ABC123", "VW GOLF", StatusEnum.OFF, new Location(0D, 0D), LocalDateTime.now()));}catch(Exception e) {}
		try{saveVehicle(new Vehicle("VLUR4X20009093588", "DEF456", "VW AMAROK", StatusEnum.OFF, new Location(0D, 0D), LocalDateTime.now()));}catch(Exception e) {}
		try{saveVehicle(new Vehicle("VLUR4X20009048066", "GHI789", "FIAT TORO", StatusEnum.OFF, new Location(0D, 0D), LocalDateTime.now()));}catch(Exception e) {}
		
		try{saveVehicle(new Vehicle("YS2R4X20005388011", "JKL012", "FORD EDGE", StatusEnum.OFF, new Location(0D, 0D), LocalDateTime.now()));}catch(Exception e) {}
		try{saveVehicle(new Vehicle("YS2R4X20005387949", "MNO345", "FORD FOCUS", StatusEnum.OFF, new Location(0D, 0D), LocalDateTime.now()));}catch(Exception e) {}
		
		try{saveVehicle(new Vehicle("YS2R4X20005387765", "PQR678", "VOLVO XC60", StatusEnum.OFF, new Location(0D, 0D), LocalDateTime.now()));}catch(Exception e) {}
		try{saveVehicle(new Vehicle("YS2R4X20005387055", "STU901", "VOLVO XC90 ", StatusEnum.OFF, new Location(0D, 0D), LocalDateTime.now()));}catch(Exception e) {}
		
		return getAllVehicle();
		
	}
	
	public Vehicle insertVehicle(Vehicle vehicle) {
		return vehicleMongoRepository.insert(vehicle);
	}
	
	public Vehicle saveVehicle(Vehicle vehicle) {
		return vehicleMongoRepository.save(vehicle);
	}
	
	public Vehicle updateStatus(final String vin, final StatusEnum status, Location location, LocalDateTime dtStatus){
		logger.info("##VehicleService#updateStatus vin {}, status {}, location {}", vin, status, location);
		Vehicle v = vehicleMongoRepository.findById(vin).orElseThrow(IllegalStateException::new);
		v.setStatus(status);
		v.setDtStatus(dtStatus);
		v.setGeolocation(location);
		vehicleMongoRepository.save(v);
		return v;
	}
	
	public List<Vehicle> findByGeolocationWithin2(double latitude, double longitude, double distance) {
		logger.info("##VehicleService#findByGeolocationWithin: {} ; {} ; {}", latitude, longitude, distance);
		return this.vehicleMongoRepository2.findByGeolocationWithin(latitude, longitude, distance);
	}
	
	public List<Vehicle> findByGeolocationWithin(double latitude, double longitude, double distance) {
		logger.info("##VehicleService#findByGeolocationWithin: {} ; {} ; {}", latitude, longitude, distance);
		Point point = new Point(latitude, longitude);
		Distance radious = new Distance(distance, Metrics.KILOMETERS);
		Circle circle = new Circle(point, radious);
		return this.vehicleMongoRepository.findByGeolocationWithin(circle);
	}
	
	public VehicleList getAllVehicle(){
		logger.info("##VehicleService#getAllVehicle: no argument");
		List<Vehicle> vehicleList = vehicleMongoRepository.findAll();
		VehicleList retur = new VehicleList(vehicleList);
		return retur;
	}
	
//	public Optional<Vehicle> getVehicleOptional(final String vin){
//		logger.info("##VehicleService#getVehicleOptional vin {}", vin);
//		return Optional.of(this.getVehicle(vin));
//	}
	
	public List<Vehicle> getVehicle(final String vin){
		logger.info("##VehicleService#getVehicle vin {}", vin);
		return vehicleMongoRepository.findByVin(vin);
	}
	
}
