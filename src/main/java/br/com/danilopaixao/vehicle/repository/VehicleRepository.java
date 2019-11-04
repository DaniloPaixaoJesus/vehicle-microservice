package br.com.danilopaixao.vehicle.repository;

import java.util.Arrays;
import java.util.List;

import br.com.danilopaixao.vehicle.enums.StatusEnum;
import br.com.danilopaixao.vehicle.model.Vehicle;

public class VehicleRepository {
	
	private static List<Vehicle> vehiclesMock = Arrays.asList(
			new Vehicle("YS2R4X20005399401", "ABC123", "VW GOLF", StatusEnum.OFF, "93418DF0R09QSDF"),
			new Vehicle("VLUR4X20009093588", "DEF456", "VW AMAROK", StatusEnum.OFF, "93418DF0R09QSDF"),
			new Vehicle("VLUR4X20009048066", "GHI789", "FIAT TORO", StatusEnum.OFF, "93418DF0R09QSDF"),
			
			new Vehicle("YS2R4X20005388011", "JKL012", "FORD EDGE", StatusEnum.OFF, "623480520FDF2"),
			new Vehicle("YS2R4X20005387949", "MNO345", "FORD FOCUS", StatusEnum.OFF, "623480520FDF2"),
			
			new Vehicle("YS2R4X20005387765", "PQR678", "VOLVO XC60", StatusEnum.OFF, "93418DF0R09QSDF"),
			new Vehicle("YS2R4X20005387055", "STU901", "VOLVO XC90 ", StatusEnum.OFF, "93418DF0R09QSDF")
	);
	
	public List<Vehicle> findAll() {
		return vehiclesMock;
	}
	
	public Vehicle updateStatus(String vin, StatusEnum status) {
		if(vin == null
				|| status == null) {
			return null;
		}
		for (Vehicle v : vehiclesMock) {
			if(v.getVin() != null
					&& v.getVin().equalsIgnoreCase(vin)) {
				v.setStatus(status);
				return v;
			}	
		}
		
		return null;
	}
	
	public Vehicle save(Vehicle vehicle) {
		if(vehicle == null
				|| vehicle.getVin() == null) {
			return null;
		}
		for (Vehicle v : vehiclesMock) {
			if(v.getVin() != null
					&& v.getVin().equalsIgnoreCase(vehicle.getVin())) {
				return v;
			}	
		}
		
		return null;
	}

	public Vehicle insert(Vehicle vehicle) {
		vehiclesMock.add(vehicle);
		return vehicle;
	}
	
	public Vehicle findByVin(String vin) {
		return vehiclesMock.stream()
				.filter(v -> v.getVin().equalsIgnoreCase(vin))
				.findAny()
				.orElse(null);
	}

}
