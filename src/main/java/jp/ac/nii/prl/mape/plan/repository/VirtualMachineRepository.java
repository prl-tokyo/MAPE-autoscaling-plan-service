package jp.ac.nii.prl.mape.plan.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.nii.prl.mape.plan.model.VirtualMachine;

public interface VirtualMachineRepository extends JpaRepository<VirtualMachine, Integer> {

	Collection<VirtualMachine> findByDeploymentId(Integer deploymentId);

}
