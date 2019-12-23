package br.com.danilopaixao.vehicle.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import br.com.danilopaixao.vehicle.model.Vehicle;


@Repository
public class VehicleMongoRepository2{
	
	@Autowired
    private MongoOperations operations;	
	
    public List<Vehicle> findByGeolocationWithin(double latitude, double longitude, double distance){
    	//(Point pointArg) {
		//Point point = new Point(-23.492877, -46.861615); //(-23.493625, -46.858718);
    	Point point = new Point(latitude, longitude);
		Distance radious = new Distance(distance, Metrics.KILOMETERS);
		Circle circle = new Circle(point, radious);
		Query query = new Query();
		query.addCriteria(Criteria.where("geolocation").withinSphere(circle));
		return operations.find(query, Vehicle.class);
    }

    
    /**
     * 
use springclouddb 
 
 
db.vehicleTrack.find({
  geolocation: {
    $geoWithin: {
      $centerSphere: [
        [-23.493625, -46.858718],
        0.1
      ]
    }
  }
})

db.vehicleTrack.find({
  geolocation: {
    $geoWithin: {
      $centerSphere: [
        [-23.507694, -46.853043],
        0.1
      ]
    }
  }
})
     */
}