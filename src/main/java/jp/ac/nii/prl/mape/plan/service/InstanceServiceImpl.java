package jp.ac.nii.prl.mape.plan.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.plan.model.Instance;
import jp.ac.nii.prl.mape.plan.repository.InstanceRepository;

@Service("instanceService")
public class InstanceServiceImpl implements InstanceService {

	@Autowired
	private InstanceRepository instanceRepository;
	
	@Autowired
	private InstanceTypeService instanceTypeService;
	
	@Override
	public Instance save(Instance instance) {
		return instanceRepository.save(instance);
	}

	@Override
	public Collection<Instance> findByDeploymentId(Integer deploymentId) {
		return instanceRepository.findByDeploymentId(deploymentId);
	}

	@Override
	public void setInstanceType(Instance instance) {
		instance.setInstanceType(instanceTypeService.findByDeploymentAndInstanceTypeIds(
				instance.getDeployment().getId(), 
				instance.getInstType()));
	}

	@Override
	public void delete(Instance instance) {
		instanceRepository.delete(instance);
	}

}
