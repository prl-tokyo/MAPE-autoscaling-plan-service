package jp.ac.nii.prl.mape.plan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.plan.model.Adaptation;
import jp.ac.nii.prl.mape.plan.repository.AdaptationRepository;

@Service("adaptationService")
public class AdaptationServiceImpl implements AdaptationService {

	@Autowired
	private AdaptationRepository adaptationRepository;
	
	@Override
	public Adaptation save(Adaptation adaptation) {
		return adaptationRepository.save(adaptation);
	}

	@Override
	public Optional<Adaptation> findByDeploymentId(Integer deploymentId) {
		return adaptationRepository.findByDeploymentId(deploymentId);
	}

}
