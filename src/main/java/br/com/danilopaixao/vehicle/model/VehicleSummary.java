package br.com.danilopaixao.vehicle.model;

import java.io.Serializable;

/**
 * 
|-----------------------------------|
| Kalles Grustransporter AB         |
| Cementvägen 8, 111 11 Södertälje  |
|-----------------------------------|
| VIN (VehicleId)       Reg. nr.    |
|-----------------------------------|
| YS2R4X20005399401     ABC123      |
| VLUR4X20009093588     DEF456      |
| VLUR4X20009048066     GHI789      |
|-----------------------------------|
 * @author user
 *
 */
public class VehicleSummary implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7901135802996014833L;
	
	private String vin;
	private String regNumber;
	private String name;
	private String driverId;
	private String status;

	private String driverName;
	private String driverLicenseCategory;
	private String driverAddress;
	
	public VehicleSummary() {}

	public VehicleSummary(String vin, String regNumber, String name, String driverId, String driverName,
			String driverLicenseCategory, String driverAddress, String status) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
