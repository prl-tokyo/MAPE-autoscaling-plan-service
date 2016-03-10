package jp.ac.nii.prl.mape.plan.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Plan {

	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	private List<VirtualMachine> shutdown;
	
	private Map<Plan, Integer> startup;
	
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

	public Map<Plan, Integer> getStartup() {
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

	public void setStartup(Map<Plan, Integer> startup) {
		this.startup = startup;
	}
	
	

}
