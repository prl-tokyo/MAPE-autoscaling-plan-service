package jp.ac.nii.prl.mape.plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.ac.nii.prl.mape.plan.service.AdaptationService;
import jp.ac.nii.prl.mape.plan.service.DeploymentService;
import jp.ac.nii.prl.mape.plan.service.InstanceService;
import jp.ac.nii.prl.mape.plan.service.InstanceTypeService;

@RestController
@Component
@RequestMapping("/deployment")
public class DeploymentController {

	@Autowired
	private InstanceService instanceService;

	@Autowired
	private AdaptationService adaptationService;
	
	@Autowired
	private DeploymentService deploymentService;
	
	@Autowired
	private InstanceTypeService instanceTypeService;
	
}
