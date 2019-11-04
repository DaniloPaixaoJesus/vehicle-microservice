package br.com.danilopaixao.vehicle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import br.com.danilopaixao.vehicle.enums.StatusEnum;
import br.com.danilopaixao.vehicle.model.VehicleTrackWSocket;


@Service
public class VehicleSocketService {
	
	@Value("${br.com.danilopaixao.service.vehicle.socket.url}")
	private String urlVehicleSocketService;
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod ="updateStatusWebSocketFallBack",
			threadPoolKey = "updateStatusWebSocketThreadPool",
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "75"),
					@HystrixProperty(name = "maxQueueSize", value = "35")
			},
			commandProperties = {
				@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
				@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
				@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
				@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
			}
	)
	public String updateStatusWebSocket(String vin, StatusEnum status) {
		String url = urlVehicleSocketService + "/api/v1/vehicle/" + vin + "/status";
		restTemplate.put(url, new VehicleTrackWSocket(vin, status));
		return vin;
	}
	public String updateStatusWebSocketFallBack(String vin, StatusEnum status) {
		return vin;
	}
	
	
}
