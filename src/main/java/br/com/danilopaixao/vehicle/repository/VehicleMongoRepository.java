package br.com.danilopaixao.vehicle.repository;

import java.util.List;

import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.danilopaixao.vehicle.model.Vehicle;

public interface VehicleMongoRepository extends MongoRepository<Vehicle, String> {
	List<Vehicle> findByGeolocationWithin(Circle circle);
}