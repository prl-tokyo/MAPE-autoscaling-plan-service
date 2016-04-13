package jp.ac.nii.prl.mape.plan.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jp.ac.nii.prl.mape.plan.model.Deployment;
import jp.ac.nii.prl.mape.plan.model.Instance;
import jp.ac.nii.prl.mape.plan.model.InstanceType;
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
	
	/**
	 * Receives a deployment, with adaptation. Calculate plan, and save the resulting 
	 * deployment. Adaptation is saved for reference.
	 * 
	 * @param deployment
	 * @return the location of the deployment
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> createDeployment(@RequestBody Deployment deployment) {
		
		// set instance <-> instanceType relationship
		for (InstanceType instType:deployment.getInstanceTypes())
			instanceTypeService.setInstances(instType);
		for (Instance instance:deployment.getInstances())
			instanceService.setInstanceTypes(instance);
		
		// save deployment
		deploymentService.save(deployment);
		for (InstanceType instType:deployment.getInstanceTypes()) {
			instanceTypeService.save(instType);
		}
		for (Instance instance:deployment.getInstances()) {
			instance.setDeployment(deployment);
			instanceService.setInstanceType(instance);
			instanceService.save(instance);
		}
		adaptationService.save(deployment.getAdaptation());
		
		// create plan
		deploymentService.plan(deployment);
		
		// create response
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(deployment.getId()).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
	
	/**
	 * Get deployment from ID
	 * @param deploymentId the ID of the deployment
	 * @return a deployment, if it exists, or and error if it doesn't
	 */
	@RequestMapping(value="/{deploymentId}", method=RequestMethod.GET)
	Deployment getDeployment(@PathVariable Integer deploymentId) {
		Optional<Deployment> deployment = deploymentService.findById(deploymentId);
		if (deployment.isPresent())
			return deployment.get();
		else
			throw new DeploymentNotFoundException(String.format("deployment %s not found",  deploymentId));
	}
}
