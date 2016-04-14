package jp.ac.nii.prl.mape.plan.service;

import java.util.Collection;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.plan.model.Deployment;
import jp.ac.nii.prl.mape.plan.model.Instance;
import jp.ac.nii.prl.mape.plan.model.InstanceType;
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
	public void setInstanceTypes(Instance instance) {
		instance.setInstanceType(instance
				.getDeployment()
				.getInstanceTypes()
				.stream()
				.filter(it -> it.getTypeID().equals(instance.getInstType()))
				.findFirst()
				.get());
		System.out.println(String.format("Instance type is %s", instance.getInstanceType().getTypeID()));
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

	@Override
	public Instance create(InstanceType type, Deployment deployment) {
		Instance instance = new Instance();
		instance.setInstID(UUID.randomUUID().toString());
		instance.setInstLoad(0d);
		instance.setInstType(type.getTypeID());
		instance.setDeployment(deployment);
		
		instanceRepository.save(instance);
		
		return instance;
	}

	@Override
	public void deleteAll(Collection<Instance> delete) {
		for (Instance instance:delete)
			delete(instance);
	}

}
