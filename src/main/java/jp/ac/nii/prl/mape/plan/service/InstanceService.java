package jp.ac.nii.prl.mape.plan.service;

import java.util.Collection;

import jp.ac.nii.prl.mape.plan.model.Deployment;
import jp.ac.nii.prl.mape.plan.model.Instance;
import jp.ac.nii.prl.mape.plan.model.InstanceType;

public interface InstanceService {
	
	Instance save(Instance instance);

	Collection<Instance> findByDeploymentId(Integer deploymentId);

	void setInstanceType(Instance instance);

	void delete(Instance instance);
	
	Instance create(InstanceType type, Deployment deployment);

}
