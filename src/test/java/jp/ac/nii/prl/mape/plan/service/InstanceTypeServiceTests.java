package jp.ac.nii.prl.mape.plan.service;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import jp.ac.nii.prl.mape.plan.model.InstanceType;
import jp.ac.nii.prl.mape.plan.repository.InstanceTypeRepository;

@RunWith(MockitoJUnitRunner.class)
public class InstanceTypeServiceTests {

	@Autowired
	@InjectMocks
	private InstanceTypeServiceImpl instanceTypeService;
	
	@Mock
	private InstanceTypeRepository instanceTypeRepository;
	
	@Before
	public void init() {
		Mockito.reset(instanceTypeRepository);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetOneAdditionalCPU() {
		InstanceType it1 = new InstanceType();
		it1.setInstances(new ArrayList<>());
		it1.setTypeID("t2.micro");
		it1.setTypeCost(0.01d);
		it1.setTypeRAM(1d);
		it1.setTypeCPUs(1);
		List<InstanceType> instanceTypes = new ArrayList<>();
		instanceTypes.add(it1);
		when(instanceTypeRepository.findAllOrderDescByTypeCPUsLessThan(1)).thenReturn(instanceTypes);
		
		Collection<InstanceType> result = instanceTypeService.getAdditionalCPUs(1);
		
		assertEquals(1, result.size());
		assertEquals(it1, result.iterator().next());
	}
	
	@Test
	public void testGetAdditionalCPUTooMany() {
		InstanceType it1 = new InstanceType();
		it1.setInstances(new ArrayList<>());
		it1.setTypeID("t2.micro");
		it1.setTypeCost(0.02d);
		it1.setTypeRAM(1d);
		it1.setTypeCPUs(1);
		
		InstanceType it2 = new InstanceType();
		it2.setInstances(new ArrayList<>());
		it2.setTypeID("t2.large");
		it2.setTypeCost(0.01d);
		it2.setTypeRAM(1d);
		it2.setTypeCPUs(2);
		
		List<InstanceType> instanceTypes = new ArrayList<>();
		instanceTypes.add(it2);
		instanceTypes.add(it1);
		
		when(instanceTypeRepository.findAllOrderDescByTypeCPUsLessThan(2)).thenReturn(instanceTypes);
		
		Collection<InstanceType> result = instanceTypeService.getAdditionalCPUs(2);
		
		assertEquals(1, result.size());
		assertEquals(it2, result.iterator().next());
	}
	
	@Test
	public void testGetMultipleAdditionalCPUs() {
		InstanceType it1 = new InstanceType();
		it1.setInstances(new ArrayList<>());
		it1.setTypeID("t2.micro");
		it1.setTypeCost(0.01d);
		it1.setTypeRAM(1d);
		it1.setTypeCPUs(1);
		
		InstanceType it2 = new InstanceType();
		it2.setInstances(new ArrayList<>());
		it2.setTypeID("t2.large");
		it2.setTypeCost(0.02d);
		it2.setTypeRAM(1d);
		it2.setTypeCPUs(2);
		
		InstanceType it3 = new InstanceType();
		it3.setInstances(new ArrayList<>());
		it3.setTypeID("t2.xlarge");
		it3.setTypeCost(0.03d);
		it3.setTypeRAM(16d);
		it3.setTypeCPUs(4);
		
		List<InstanceType> instanceTypes = new ArrayList<>();
		instanceTypes.add(it3);
		instanceTypes.add(it2);
		instanceTypes.add(it1);
		
		when(instanceTypeRepository.findAllOrderDescByTypeCPUsLessThan(6)).thenReturn(instanceTypes);
		
		Collection<InstanceType> result = instanceTypeService.getAdditionalCPUs(6);
		
		assertEquals(2, result.size());
		Iterator<InstanceType> iter = result.iterator();
		assertEquals(it3, iter.next());
		assertEquals(it2, iter.next());
	}
	
	@Test
	public void testGetSeveralIdenticalTypesAdditionalCPUs() {
		InstanceType it1 = new InstanceType();
		it1.setInstances(new ArrayList<>());
		it1.setTypeID("t2.micro");
		it1.setTypeCost(0.01d);
		it1.setTypeRAM(1d);
		it1.setTypeCPUs(1);
		
		InstanceType it2 = new InstanceType();
		it2.setInstances(new ArrayList<>());
		it2.setTypeID("t2.large");
		it2.setTypeCost(0.02d);
		it2.setTypeRAM(1d);
		it2.setTypeCPUs(2);
		
		List<InstanceType> instanceTypes = new ArrayList<>();
		instanceTypes.add(it2);
		instanceTypes.add(it1);
		
		when(instanceTypeRepository.findAllOrderDescByTypeCPUsLessThan(7)).thenReturn(instanceTypes);
		
		Collection<InstanceType> result = instanceTypeService.getAdditionalCPUs(7);
		
		assertEquals(4, result.size());
		Iterator<InstanceType> iter = result.iterator();
		assertEquals(it2, iter.next());
		assertEquals(it2, iter.next());
		assertEquals(it2, iter.next());
		assertEquals(it1, iter.next());
	}
}
