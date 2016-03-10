package jp.ac.nii.prl.mape.plan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

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
	
	private double load1;
	
	private double load5;
	
	private double load10;
	
	@Min(0)
	private int cpus;
	
	public double getAverageLoadPerCPU(int type) {
		return getLoad(type) / cpus;
	}

	public int getCpus() {
		return cpus;
	}

	public Deployment getDeployment() {
		return deployment;
	}
	
	public String getId() {
		return id;
	}
	
	private double getLoad(int type) {
		switch(type) {
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
	
	public void setCpus(int cpus) {
		this.cpus = cpus;
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
	
	@Override
	public String toString() {
		return String.format("VM [%s]: %d vCPUs, load: %4.2f - %4.2f - %4.2f, deployment: %s[%s]", 
				vmId, cpus, load1, load5, load10, deployment.getId(), id);
	}
}
