package jp.ac.nii.prl.mape.plan.service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.plan.model.Deployment;
import jp.ac.nii.prl.mape.plan.model.Instance;
import jp.ac.nii.prl.mape.plan.repository.DeploymentRepository;

@Service("deploymentService")
public class DeploymentServiceImpl implements DeploymentService {

	@Autowired
	private DeploymentRepository deploymentRepository;
	
	@Autowired
	private InstanceService instanceService;
	
	@Override
	public Deployment save(Deployment deployment) {
		return deploymentRepository.save(deployment);
	}

	@Override
	public Collection<Deployment> findAll() {
		return deploymentRepository.findAll();
	}

	@Override
	public Optional<Deployment> findById(Integer deploymentId) {
		return deploymentRepository.findById(deploymentId);
	}

	@Override
	public void addInstance(Integer deploymentId, String instType) {
		String instID = UUID.randomUUID().toString();
		Instance instance = new Instance();
		instance.setInstID(instID);
		instance.setInstType(instType);
		instance.setInstLoad(0d);
		instance.setDeployment(findById(deploymentId).get());
	}

	@Override
	public void deleteInstance(Integer deploymentId, Instance instance) {
		Deployment deployment = findById(deploymentId).get();
		deployment.getInstances().remove(instance);
		instanceService.delete(instance);
	}
}
