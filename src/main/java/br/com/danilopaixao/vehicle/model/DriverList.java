package br.com.danilopaixao.vehicle.model;

import java.io.Serializable;
import java.util.List;

public class DriverList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3182987159693795275L;
	
	public DriverList() {}
	
	public DriverList(List<Driver> driverList) {
		super();
		this.driverList = driverList;
	}

	private List<Driver> driverList;

	public List<Driver> getDriverList() {
		return driverList;
	}

	public void setDriverList(List<Driver> driverList) {
		this.driverList = driverList;
	}

}
