package jp.ac.nii.prl.mape.plan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.plan.repository.InstanceTypeRepository;

@Service("instanceTypeService")
public class InstanceTypeServiceImpl implements InstanceTypeService {

	@Autowired
	private InstanceTypeRepository instanceTypeRepository;
}
