package jp.ac.nii.prl.mape.plan.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.plan.model.InstanceType;
import jp.ac.nii.prl.mape.plan.repository.InstanceTypeRepository;

@Service("instanceTypeService")
public class InstanceTypeServiceImpl implements InstanceTypeService {

	@Autowired
	private InstanceTypeRepository instanceTypeRepository;

	@Override
	public void save(InstanceType instType) {
		instanceTypeRepository.save(instType);
	}

	@Override
	public InstanceType findByDeploymentAndInstanceTypeIds(Integer deploymentId, String instType) {
		Collection<InstanceType> instanceTypes = instanceTypeRepository.findByDeploymentId(deploymentId);
		for (InstanceType instanceType:instanceTypes) {
			if (instanceType.getTypeID().equals(instType))
				return instanceType;
		}
		return null;
	}
}
