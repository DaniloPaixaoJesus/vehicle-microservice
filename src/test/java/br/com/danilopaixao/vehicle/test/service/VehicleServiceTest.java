package br.com.danilopaixao.vehicle.test.service;


import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.danilopaixao.vehicle.enums.StatusEnum;
import br.com.danilopaixao.vehicle.model.Location;
import br.com.danilopaixao.vehicle.model.Vehicle;
import br.com.danilopaixao.vehicle.repository.VehicleMongoRepository;
import br.com.danilopaixao.vehicle.service.DriverService;
import br.com.danilopaixao.vehicle.service.VehicleService;
import br.com.danilopaixao.vehicle.test.builder.VehicleTestBuilder;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {

	@InjectMocks
	private VehicleService vehicleService;
	
	@Mock
	private DriverService driverService;
	
	@Mock
	private VehicleMongoRepository vehicleMongoRepository;
	
	private VehicleTestBuilder vehicleBuilder;
	
	@Before
    public void init() {
		vehicleBuilder = new VehicleTestBuilder();
    }
	
	@Test
	public void testUpdateVehicleON() throws Exception{
		Vehicle vehicleGet = vehicleBuilder.buildRandom(StatusEnum.OFF);
		Optional<Vehicle> vOptional = Optional.of(vehicleGet);
		when(vehicleMongoRepository.findById(vehicleGet.getVin())).thenReturn(vOptional);
		Vehicle vReturned = vehicleService.updateStatus(vehicleGet.getVin(), StatusEnum.ON, new Location(), LocalDateTime.now());
		assertSame(StatusEnum.ON, vReturned.getStatus());
	}
	
	@Test
	public void testUpdateVehicleOFF() throws Exception{
		Vehicle vehicleGet = vehicleBuilder.buildRandom(StatusEnum.ON);
		Optional<Vehicle> vOptional = Optional.of(vehicleGet);
		when(vehicleMongoRepository.findById(vehicleGet.getVin())).thenReturn(vOptional);
		Vehicle vReturned = vehicleService.updateStatus(vehicleGet.getVin(), StatusEnum.OFF, new Location(), LocalDateTime.now());
		assertSame(StatusEnum.OFF, vReturned.getStatus());
	}
		
	@SuppressWarnings("static-access")
	@Test
	public void testUpdateVehicleNotFound() throws Exception{
		Vehicle vehicleGet = vehicleBuilder.buildRandom(StatusEnum.ON);
		when(vehicleMongoRepository.findById(vehicleGet.getVin()))
						.thenReturn(Optional.ofNullable(null).empty());
		Vehicle vReturned = vehicleService.updateStatus(vehicleGet.getVin(), StatusEnum.OFF, new Location(), LocalDateTime.now());
		assertSame(null, vReturned);
	}
	
}
