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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((licenseCategory == null) ? 0 : licenseCategory.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (licenseCategory == null) {
			if (other.licenseCategory != null)
				return false;
		} else if (!licenseCategory.equals(other.licenseCategory))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Driver [id=" + id + ", name=" + name + ", licenseCategory=" + licenseCategory + ", address=" + address
				+ "]";
	}
	
	

}
