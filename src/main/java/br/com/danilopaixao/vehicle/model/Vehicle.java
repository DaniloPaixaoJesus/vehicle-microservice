package br.com.danilopaixao.vehicle.model;

public class Vehicle {
	
	private String name;
	private String desc;
	private int rating;
	
	private String driverName;
	private String driverId;

	public Vehicle(String name, String desc, int rating, String driverName, String driverId) {
		super();
		this.name = name;
		this.desc = desc;
		this.rating = rating;
		this.driverId = driverId;
		this.driverName = driverName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
}
