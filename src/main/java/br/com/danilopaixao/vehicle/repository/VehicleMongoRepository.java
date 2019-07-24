package br.com.danilopaixao.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.danilopaixao.vehicle.model.Vehicle;

public interface VehicleMongoRepository extends MongoRepository<Vehicle, String> {
}