package br.com.danilopaixao.vehicle.model;

import java.io.Serializable;

import br.com.danilopaixao.vehicle.enums.StatusEnum;


public class VehicleSummary implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7901135802996014833L;
	
	private String vin;
	private String regNumber;
	private String name;
	private String driverId;
	private StatusEnum status;

	private String driverName;
	private String driverLicenseCategory;
	private String driverAddress;
	
	public VehicleSummary() {}

	public VehicleSummary(String vin, String regNumber, String name, String driverId, String driverName,
			String driverLicenseCategory, String driverAddress, StatusEnum status) {
		super();
		this.vin = vin;
		this.regNumber = regNumber;
		this.name = name;
		this.driverId = driverId;
		this.driverName = driverName;
		this.driverLicenseCategory = driverLicenseCategory;
		this.driverAddress = driverAddress;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDriverAddress() {
		return driverAddress;
	}

	public void setDriverAddress(String driverAddress) {
		this.driverAddress = driverAddress;
	}

	public String getDriverLicenseCategory() {
		return driverLicenseCategory;
	}

	public void setDriverLicenseCategory(String driverLicenseCategory) {
		this.driverLicenseCategory = driverLicenseCategory;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((driverAddress == null) ? 0 : driverAddress.hashCode());
		result = prime * result + ((driverId == null) ? 0 : driverId.hashCode());
		result = prime * result + ((driverLicenseCategory == null) ? 0 : driverLicenseCategory.hashCode());
		result = prime * result + ((driverName == null) ? 0 : driverName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((regNumber == null) ? 0 : regNumber.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((vin == null) ? 0 : vin.hashCode());
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
		VehicleSummary other = (VehicleSummary) obj;
		if (driverAddress == null) {
			if (other.driverAddress != null)
				return false;
		} else if (!driverAddress.equals(other.driverAddress))
			return false;
		if (driverId == null) {
			if (other.driverId != null)
				return false;
		} else if (!driverId.equals(other.driverId))
			return false;
		if (driverLicenseCategory == null) {
			if (other.driverLicenseCategory != null)
				return false;
		} else if (!driverLicenseCategory.equals(other.driverLicenseCategory))
			return false;
		if (driverName == null) {
			if (other.driverName != null)
				return false;
		} else if (!driverName.equals(other.driverName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (regNumber == null) {
			if (other.regNumber != null)
				return false;
		} else if (!regNumber.equals(other.regNumber))
			return false;
		if (status != other.status)
			return false;
		if (vin == null) {
			if (other.vin != null)
				return false;
		} else if (!vin.equals(other.vin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VehicleSummary [vin=" + vin + ", regNumber=" + regNumber + ", name=" + name + ", driverId=" + driverId
				+ ", status=" + status + ", driverName=" + driverName + ", driverLicenseCategory="
				+ driverLicenseCategory + ", driverAddress=" + driverAddress + "]";
	}

}
