package jp.ac.nii.prl.mape.plan.service;

import java.util.Collection;
import java.util.Optional;

import jp.ac.nii.prl.mape.plan.model.Deployment;
import jp.ac.nii.prl.mape.plan.model.Instance;

public interface DeploymentService {

	Deployment save(Deployment deployment);

	Collection<Deployment> findAll();

	Optional<Deployment> findById(Integer deploymentId);
	
	void addInstance(Integer deploymentId, String instType);
	
	void deleteInstance(Integer deploymentId, Instance instance);
	
}
