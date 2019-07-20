package br.com.danilopaixao.vehicle.model;

import java.io.Serializable;

public class Driver implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3536589143277551856L;
	
	private String id;
	private String name;
	private String licenseCategory;
	private String address;
	
	public Driver() {
	}
	
	
	public Driver(String id, String name, String licenseCategory, String address) {
		super();
		this.id = id;
		this.name = name;
		this.licenseCategory = licenseCategory;
		this.address = address;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getLicenseCategory() {
		return licenseCategory;
	}


	public void setLicenseCategory(String licenseCategory) {
		this.licenseCategory = licenseCategory;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
