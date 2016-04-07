package jp.ac.nii.prl.mape.plan.service;

import jp.ac.nii.prl.mape.plan.model.InstanceType;

public interface InstanceTypeService {

	void save(InstanceType instType);

	InstanceType findByDeploymentAndInstanceTypeIds(Integer id, String instType);

}