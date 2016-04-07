package jp.ac.nii.prl.mape.plan.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Deployment {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@JsonIgnore
	@OneToOne(mappedBy="deployment")
	private Adaptation adaptation;

	@OneToMany(mappedBy="deployment")
	private List<Instance> vms;
	
	@OneToMany(mappedBy="deployment")
	private List<InstanceType> instanceTypes;
	
	public Adaptation getAdaptation() {
		return adaptation;
	}

	public Integer getId() {
		return id;
	}

	public List<InstanceType> getInstanceTypes() {
		return instanceTypes;
	}

	public List<Instance> getVms() {
		return vms;
	}

	public void setAdaptation(Adaptation adaptation) {
		this.adaptation = adaptation;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setInstanceTypes(List<InstanceType> instanceTypes) {
		this.instanceTypes = instanceTypes;
	}

	public void setVms(List<Instance> vms) {
		this.vms = vms;
	}
	
	public int size() {
		return vms.size();
	}

	public String toString() {
		return String.format("Deployment %d with %d virtual machines", id, vms.size());
	}

}
