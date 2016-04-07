package jp.ac.nii.prl.mape.plan.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.nii.prl.mape.plan.model.Instance;

public interface InstanceRepository extends JpaRepository<Instance, Integer> {

	Collection<Instance> findByDeploymentId(Integer deploymentId);

}
