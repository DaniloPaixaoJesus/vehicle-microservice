package br.com.danilopaixao.vehicle.model;

import java.io.Serializable;

public class VehicleTrackWSocket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8659998311539411802L;
	
	private String vin;
	private String status;

	public VehicleTrackWSocket() {
	}
	
	public VehicleTrackWSocket(String vin, String status) {
		super();
		this.setVin(vin);
		this.setStatus(status);
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
