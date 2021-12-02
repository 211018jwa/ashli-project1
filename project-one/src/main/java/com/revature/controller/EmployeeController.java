package com.revature.controller;

import com.revature.model.User;
import com.revature.service.AuthorizationService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class EmployeeController implements Controller {
	
	private AuthorizationService authorizationService;
	
	public EmployeeController() {
		this.authorizationService = new AuthorizationService();
	}
	//this is a "protected" endpoint that can only be accessed when you are logged in as either employee or finance manager
	private Handler getEmployeeById = (ctx) -> {
		User user = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authorizationService.authroizeEmployeeAndFinanceManager(user); //authorizes employees and finance managers
		
		//other logic
		String id = ctx.pathParam("employeeId");
	};
	
	//this can only be accessed when you are logged in as a finance manager
	private Handler addEmployee = (ctx) -> {
		User user = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authorizationService.authorizeFinanceManager(user); //authorizes finance managers
		
		//other logic
		
	};
	

	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/employees/{employeeId}", getEmployeeById);
		app.post("/employees", addEmployee);
	
		
	}
	

}
