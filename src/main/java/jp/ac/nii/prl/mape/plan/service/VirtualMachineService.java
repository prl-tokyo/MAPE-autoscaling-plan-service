package jp.ac.nii.prl.mape.plan.service;

import java.util.Collection;

import jp.ac.nii.prl.mape.plan.model.VirtualMachine;

public interface VirtualMachineService {
	
	VirtualMachine save(VirtualMachine virtualMachine);

	Collection<VirtualMachine> findByDeploymentId(Integer deploymentId);

}
