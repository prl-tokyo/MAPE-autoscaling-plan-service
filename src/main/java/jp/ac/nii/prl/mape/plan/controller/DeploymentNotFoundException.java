package jp.ac.nii.prl.mape.plan.controller;

import org.springframework.web.client.RestClientException;

public class DeploymentNotFoundException extends RestClientException {

	private static final long serialVersionUID = -4969288164849609247L;

	public DeploymentNotFoundException(String msg) {
		super(msg);
	}

	public DeploymentNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
