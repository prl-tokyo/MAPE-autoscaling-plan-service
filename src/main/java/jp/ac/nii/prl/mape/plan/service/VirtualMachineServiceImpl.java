package jp.ac.nii.prl.mape.plan.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.plan.model.VirtualMachine;
import jp.ac.nii.prl.mape.plan.repository.VirtualMachineRepository;

@Service("virtualMachineService")
public class VirtualMachineServiceImpl implements VirtualMachineService {

	@Autowired
	private VirtualMachineRepository virtualMachineRepository;
	
	@Override
	public VirtualMachine save(VirtualMachine virtualMachine) {
		return virtualMachineRepository.save(virtualMachine);
	}

	@Override
	public Collection<VirtualMachine> findByDeploymentId(Integer deploymentId) {
		return virtualMachineRepository.findByDeploymentId(deploymentId);
	}

}
