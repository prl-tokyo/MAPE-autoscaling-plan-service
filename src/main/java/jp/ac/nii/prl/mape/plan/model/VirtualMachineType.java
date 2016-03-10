package jp.ac.nii.prl.mape.plan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VirtualMachineType {

	@GeneratedValue
	@Id
	@JsonIgnore
	private Integer id;
	
	private String name;
	
	private int cpuCount;
	
	private int ram;
	
	private double pricePerHour;

	public int getCpuCount() {
		return cpuCount;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public int getRam() {
		return ram;
	}

	public void setCpuCount(int cpuCount) {
		this.cpuCount = cpuCount;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}
}
