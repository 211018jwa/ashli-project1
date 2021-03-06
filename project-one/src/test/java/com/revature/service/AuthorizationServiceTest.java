package com.revature.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.exception.UnauthorizedException;
import com.revature.model.User;

public class AuthorizationServiceTest {

private AuthorizationService authService;
	
	@BeforeEach
	public void setup() {
		this.authService = new AuthorizationService();
	}
	
	
	/*
	 * authorizefm
	 */
	@Test
	public void authorizeFinanceManager_negativeTest_userIsEmployeeButRequiresFinanceManagerPermissions() throws UnauthorizedException {
		User user = new User(1, "Carter", "ONeal","carterone@yahoo.com", "carter_one", "password$", "Employee"); 
		
		
		Assertions.assertThrows(UnauthorizedException.class ,() -> {
			this.authService.authorizeFinanceManager(user);
		});
		
	}
	
	@Test
	public void authorizeFinanceManager_negativeTest_userIsNull() {		
		Assertions.assertThrows(UnauthorizedException.class, () -> {
			this.authService.authorizeFinanceManager(null);
		});
	}
	
	@Test
	public void authorizeFinanceManager_positiveTest_userIsAFinanceManager() throws UnauthorizedException {
		User user = new User(1, "Carter", "ONeal","carterone@yahoo.com", "carter_one", "password$", "Finance Manager"); 
		
		this.authService.authorizeFinanceManager(user);
	}
	
	/*
	 * authorizeem
	 */
	@Test
	public void authorizeEmployee_negativeTest_userIsFinanceManagerButRequiresEmployeeRole() {
		User user = new User(1, "Carter", "ONeal","carterone@yahoo.com", "carter_one", "password$", "Finance Manager"); 
		
		Assertions.assertThrows(UnauthorizedException.class, () -> {
			this.authService.authorizeEmployee(user);
		});
		
	}

	@Test
	public void authorizeEmployee_negativeTest_userIsNull() {
		Assertions.assertThrows(UnauthorizedException.class, () -> {
			this.authService.authorizeEmployee(null);
		});
	}
	
	@Test
	public void authorizeEmployee_positiveTest_userIsAnEmployee() throws UnauthorizedException {
		User user = new User(1, "Carter", "ONeal","carterone@yahoo.com", "carter_one", "password$", "Employee"); 
		
		this.authService.authorizeEmployee(user);
	}
	
	/*
	 * authorizeEM/FM
	 */
	@Test
	public void authorizeEmployeeAndFinanceManager_negativeTest_userIsNotEmployeeOrFinanceManager() {
		User user = new User(1, "Carter", "ONeal","carterone@yahoo.com", "carter_one", "password$", "new user"); 
		
		Assertions.assertThrows(UnauthorizedException.class, () -> {
			this.authService.authroizeEmployeeAndFinanceManager(user);
		});
	}
	
	@Test
	public void authorizeEmployeeAndFinanceManager_negativeTest_userIsNull() {
		Assertions.assertThrows(UnauthorizedException.class, () -> {
			this.authService.authroizeEmployeeAndFinanceManager(null);
		});
	}
	
	@Test
	public void authroizeEmployeeAndFinanceManager_positiveTest_userIsEmployee() throws UnauthorizedException {
		User user = new User(1, "Carter", "ONeal","carterone@yahoo.com", "carter_one", "password$", "Employee"); 
		
		this.authService.authroizeEmployeeAndFinanceManager(user);
	}
	
	@Test
	public void authorizeEmployeeAndFinanceManager_positiveTest_userIsFinanceManager() throws UnauthorizedException {
		User user = new User(1, "Carter", "ONeal","carterone@yahoo.com", "carter_one", "password$", "Finance Manager"); 
		
		this.authService.authroizeEmployeeAndFinanceManager(user);
	}
}
