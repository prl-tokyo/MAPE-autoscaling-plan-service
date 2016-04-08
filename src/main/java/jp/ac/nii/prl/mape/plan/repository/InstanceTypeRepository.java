package jp.ac.nii.prl.mape.plan.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.nii.prl.mape.plan.model.InstanceType;

public interface InstanceTypeRepository extends JpaRepository<InstanceType, Integer>{

	Collection<InstanceType> findByDeploymentId(Integer deploymentId);

	List<InstanceType> findAllOrderDescByTypeCPUsLessThan(int nCpus);

}
