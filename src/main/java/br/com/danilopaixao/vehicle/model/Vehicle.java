package br.com.danilopaixao.vehicle.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.danilopaixao.vehicle.enums.StatusEnum;

@Document(collection = "vehicle")
public class Vehicle implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1564541072875906508L;
	
	@Id
	private String vin;
	private String regNumber;
	private String name;
	private String driverId;
	private StatusEnum status;

	public Vehicle() {}

	public Vehicle(String vin, String regNumber, String name, StatusEnum status, String driverId) {
		super();
		this.vin = vin;
		this.regNumber = regNumber;
		this.name = name;
		this.status = status;
		this.driverId = driverId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
