package jp.ac.nii.prl.mape.plan.service;

import java.util.Collection;
import java.util.Optional;

import jp.ac.nii.prl.mape.plan.model.Deployment;

public interface DeploymentService {

	Deployment save(Deployment deployment);

	Collection<Deployment> findAll();

	Optional<Deployment> findById(Integer deploymentId);
	
}
