package com.lannister.sparkhire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactoryImpl implements ServiceFactory{

	@Autowired
	private StaffService staffService;
	
	

	@Override
	public StaffService getStaffService() {
		return staffService;
	}

}
