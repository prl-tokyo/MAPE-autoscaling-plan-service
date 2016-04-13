package jp.ac.nii.prl.mape.plan.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.plan.model.Instance;
import jp.ac.nii.prl.mape.plan.model.InstanceType;
import jp.ac.nii.prl.mape.plan.repository.InstanceTypeRepository;

@Service("instanceTypeService")
public class InstanceTypeServiceImpl implements InstanceTypeService {

	@Autowired
	private InstanceTypeRepository instanceTypeRepository;

	@Override
	public void save(InstanceType instType) {
		instanceTypeRepository.save(instType);
	}
	
	@Override
	public void setInstances(InstanceType instType) {
		List<Instance> instances = instType
				.getDeployment()
				.getInstances()
				.stream()
				.filter(i -> i.getInstType().equals(instType.getTypeID()))
				.collect(Collectors.toList());
		instType.setInstances(instances);
	}

	@Override
	public InstanceType findByDeploymentAndInstanceTypeIds(Integer deploymentId, String instType) {
		Collection<InstanceType> instanceTypes = instanceTypeRepository.findByDeploymentId(deploymentId);
		for (InstanceType instanceType:instanceTypes) {
			if (instanceType.getTypeID().equals(instType))
				return instanceType;
		}
		return null;
	}

	@Override
	public Collection<InstanceType> getAdditionalCPUs(int nCpus) {
		Collection<InstanceType> res = new ArrayList<>();
		List<InstanceType> available = instanceTypeRepository.findAllOrderDescByTypeCPUsLessThan(nCpus);
		
		int numberLeft = nCpus;
		while (numberLeft > 0) {
			InstanceType newInstance = available.get(0);
			res.add(newInstance);
			numberLeft -= newInstance.getTypeCPUs();
			List<InstanceType> newAvailable = new ArrayList<>();
			for (InstanceType iType:available) {
				if (iType.getTypeCPUs() <= numberLeft)
					newAvailable.add(iType);
			}
			available = newAvailable;
		}
		return res;
	}
}
