package jp.ac.nii.prl.mape.plan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.nii.prl.mape.plan.model.Deployment;

public interface DeploymentRepository extends JpaRepository<Deployment, Integer> {

	Optional<Deployment> findById(Integer deploymentId);

}
