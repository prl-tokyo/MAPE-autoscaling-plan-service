package jp.ac.nii.prl.mape.plan.service;

import java.util.Collection;

import jp.ac.nii.prl.mape.plan.model.Instance;

public interface InstanceService {
	
	Instance save(Instance instance);

	Collection<Instance> findByDeploymentId(Integer deploymentId);

	void setInstanceType(Instance instance);

	void delete(Instance instance);

}
