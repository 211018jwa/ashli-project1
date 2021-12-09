package com.revature.service;

import com.revature.exception.UnauthorizedException;
import com.revature.model.User;

public class AuthorizationService {

	// authentication = providing credentials to identify who you are
	// authorization = checking whether you have permission to access a specific
	// thing

	public void authroizeEmployeeAndFinanceManager(User user) throws UnauthorizedException {
		if (user == null || !(user.getUser_role().equals("Employee") || user.getUser_role().equals("Finance Manager"))) { //short-circuiting
			throw new UnauthorizedException("You must be an employee or finance manager to access this resource");
		}
	}

	public void authorizeFinanceManager(User user) throws UnauthorizedException {
			if(user == null || !(user.getUser_role().equals("Finance Manager"))) {
				throw new UnauthorizedException("You must be a finance manager to access this resource");
			}	
	}
	public void authorizeEmployee(User user) throws UnauthorizedException {
		if(user == null || !(user.getUser_role().equals("Employee"))) {
			throw new UnauthorizedException("You must be an employee to access this resource");
		}	
}
}
