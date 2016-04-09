package jp.ac.nii.prl.mape.plan.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.plan.model.Deployment;
import jp.ac.nii.prl.mape.plan.model.Instance;
import jp.ac.nii.prl.mape.plan.model.InstanceType;
import jp.ac.nii.prl.mape.plan.repository.DeploymentRepository;

@Service("deploymentService")
public class DeploymentServiceImpl implements DeploymentService {

	@Autowired
	private DeploymentRepository deploymentRepository;
	
	@Autowired
	private InstanceService instanceService;
	
	@Autowired
	private InstanceTypeService instanceTypeService;
	
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

	/**
	 * Creates a plan as per the adaptation instructions, adding or terminating instances as 
	 * necessary. The plan is simply a modification of the deployment.
	 * The adaptation is left untouched.
	 * If no adaptation is necessary, there is no plan, and the deployment is not modified.
	 */
	@Override
	public void plan(Deployment deployment) {
		if (!deployment.getAdaptation().isAdapt())
			return;
		// we need to adapt
		if (deployment.getAdaptation().isScaleUp()) { // we add instances
			Collection<InstanceType> add = instanceTypeService.getAdditionalCPUs(deployment.getAdaptation().getCpuCount());
			for (InstanceType instanceType:add) {
				instanceService.create(instanceType, deployment);
			}
			return;
		} else { // we remove instances
			Collection<Instance> delete = selectInstancesToTerminate(deployment);
			instanceService.deleteAll(delete);
			return;
		}
	}
	
	/**
	 * Selects a set of instances to terminate. The goal is to terminate the number of CPUs required
	 * in the adaptation analysis. In some cases, we may end up terminating less CPUs than the
	 * objective, but never more.
	 * 
	 * The selection strategy chooses the largest instances in priority, as to minimise the number 
	 * of instances to terminate.
	 * 
	 * @param deployment
	 * @return a set of instances to terminate.
	 */
	private Collection<Instance> selectInstancesToTerminate(Deployment deployment) {
		Collection<Instance> delete = new ArrayList<>();
		int nCpus = deployment.getAdaptation().getCpuCount();
		
		List<Instance> available = deployment.getInstances();
		Collections.sort(available, (i1, i2) -> i1.getInstanceType().getTypeCPUs().compareTo(i2.getInstanceType().getTypeCPUs()));
		
		for (Instance inst:available) {
			if ((inst.getInstanceType().getTypeCPUs() <= nCpus)
					&& (!delete.contains(inst))
					&& (nCpus > 0)) {
				delete.add(inst);
				nCpus -= inst.getInstanceType().getTypeCPUs();
			}
		}
		
		return delete;
	}
}
