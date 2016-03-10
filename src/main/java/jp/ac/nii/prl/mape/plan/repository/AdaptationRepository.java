package jp.ac.nii.prl.mape.plan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.nii.prl.mape.plan.model.Adaptation;

public interface AdaptationRepository extends JpaRepository<Adaptation, Integer> {

	Optional<Adaptation> findByDeploymentId(Integer deploymentId);

}
