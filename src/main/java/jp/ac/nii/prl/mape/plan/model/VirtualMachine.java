package jp.ac.nii.prl.mape.plan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VirtualMachine {
	public static final int LOAD1 = 0;
	public static final int LOAD5 = 1;
	public static final int LOAD10 = 2;
	
	@JsonIgnore
	@ManyToOne
	private Deployment deployment;
	
	@GeneratedValue
	@JsonIgnore
	@Id
	private Integer vmId;
	
	@NotEmpty
	private String id;
	
	private VirtualMachineType type;
	
	private double load1;

	private double load5;

	private double load10;

	public double getAverageLoadPerCPU(int duration) {
		return getLoad(duration) / type.getCpuCount();
	}
	
	public int getCpus() {
		return type.getCpuCount();
	}
	
	public Deployment getDeployment() {
		return deployment;
	}
	
	public String getId() {
		return id;
	}
	
	private double getLoad(int duration) {
		switch(duration) {
			case LOAD1:
				return load1;
			case LOAD5:
				return load5;
			case LOAD10:
				return load10;
			default:
				return 0.00;
		}
	}

	public double getLoad1() {
		return load1;
	}

	public double getLoad10() {
		return load10;
	}
	
	public double getLoad5() {
		return load5;
	}
	
	public VirtualMachineType getType() {
		return type;
	}
	
	public Integer getVmId() {
		return vmId;
	}
		
	public void setDeployment(Deployment deployment) {
		this.deployment = deployment;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setLoad1(double load1) {
		this.load1 = load1;
	}
	
	public void setLoad10(double load10) {
		this.load10 = load10;
	}
	
	public void setLoad5(double load5) {
		this.load5 = load5;
	}
	
	public void setType(VirtualMachineType type) {
		this.type = type;
	}
	
	public void setVmId(Integer vmId) {
		this.vmId = vmId;
	}
	
	@Override
	public String toString() {
		return String.format("VM [%s]: type %s, %d CPUs, load: %4.2f - %4.2f - %4.2f, deployment: %s[%s]", 
				vmId, type.getName(), type.getCpuCount(), load1, load5, load10, deployment.getId(), id);
	}
}
