package br.com.danilopaixao.vehicle.queue;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.danilopaixao.vehicle.model.VehicleTrack;
import br.com.danilopaixao.vehicle.service.VehicleService;

@Component
public class VehicleQueueConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(VehicleQueueConsumer.class);
	
	@Autowired
	private VehicleService vehicleService;
	
    @RabbitListener(queues = {"${queue.vehicle}"})
    public void receive(@Payload String payload) {
    	
    	logger.info("###### VehicleQueueConsumer#receive:{}", payload);
    	
    	ObjectMapper jsonMapper = new ObjectMapper();
    	VehicleTrack vehicleTrackPayload = null;
      	try {
			vehicleTrackPayload = jsonMapper.readValue(payload, VehicleTrack.class);
			vehicleService.updateStatus(vehicleTrackPayload.getVin(), 
										vehicleTrackPayload.getStatus(), 
										vehicleTrackPayload.getGeolocation(),
										vehicleTrackPayload.getDtStatus());
		} catch (IOException e) {
			logger.error(e.getCause().getMessage());
			throw new RuntimeException(e);
		}
    	
    }
}
