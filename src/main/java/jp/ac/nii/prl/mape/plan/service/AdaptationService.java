package jp.ac.nii.prl.mape.plan.service;

import java.util.Optional;

import jp.ac.nii.prl.mape.plan.model.Adaptation;

public interface AdaptationService {

	Adaptation save(Adaptation adaptation);
	
	Optional<Adaptation> findByDeploymentId(Integer deploymentId);
	
}
