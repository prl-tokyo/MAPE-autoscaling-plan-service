package jp.ac.nii.prl.mape.plan.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Plan {

	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	@OneToMany
	private List<VirtualMachine> shutdown;
	
	@OneToMany
	private List<VirtualMachineType> startup;
	
	@OneToOne
	@JsonIgnore
	private Deployment deployment;

	public Deployment getDeployment() {
		return deployment;
	}

	public Long getId() {
		return id;
	}

	public List<VirtualMachine> getShutdown() {
		return shutdown;
	}

	public List<VirtualMachineType> getStartup() {
		return startup;
	}

	public void setDeployment(Deployment deployment) {
		this.deployment = deployment;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setShutdown(List<VirtualMachine> shutdown) {
		this.shutdown = shutdown;
	}

	public void setStartup(List<VirtualMachineType> startup) {
		this.startup = startup;
	}
	
	

}
