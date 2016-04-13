package jp.ac.nii.prl.mape.plan.service;

import java.util.Collection;

import jp.ac.nii.prl.mape.plan.model.InstanceType;

public interface InstanceTypeService {

	void save(InstanceType instType);

	InstanceType findByDeploymentAndInstanceTypeIds(Integer id, String instType);
	
	Collection<InstanceType> getAdditionalCPUs(int nCpus);

	void setInstances(InstanceType instType);

}