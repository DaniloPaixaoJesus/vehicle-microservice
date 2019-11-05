package br.com.danilopaixao.vehicle.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import br.com.danilopaixao.vehicle.model.Driver;
import br.com.danilopaixao.vehicle.model.DriverList;


@Service
public class DriverService {
	
	private static final Logger logger = LoggerFactory.getLogger(DriverService.class);
	
	@Value("${br.com.danilopaixao.service.driver.url}")
	private static String urlDriverService;
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod ="getAllDriverFallBack",
			threadPoolKey = "getAllDriverThreadPool",
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
	public DriverList getAllDriver() {
		logger.info("##DriverService#getAllDriver: no argument");
		return restTemplate.getForObject(urlDriverService+"/drivers", DriverList.class);
	}
	public DriverList getAllDriverFallBack() {
		return new DriverList(Arrays.asList(new Driver("UNAVAILABLE", "", "", "")));
	}
	
	@HystrixCommand(fallbackMethod ="getDriverFallBack",
			threadPoolKey = "getDriverThreadPool",
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
	public Driver getDriver(final String id) {
		logger.info("##DriverService#getDriver id: {} ", id);
		return restTemplate.getForObject(urlDriverService+"/drivers/"+id, Driver.class);
	}
	public Driver getDriverFallBack(final String id) {
		logger.error("##DriverService#getDriver id: {} ", id);
		return new Driver("UNAVAILABLE", "", "", "");
	}
	
}
