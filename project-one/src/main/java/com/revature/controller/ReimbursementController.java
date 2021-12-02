package com.revature.controller;

import java.util.List;

import com.revature.dto.ChangeReimbursementStatusDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.AuthorizationService;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController implements Controller {

	private AuthorizationService authorizationService; 
	private ReimbursementService reimbursementService;
	
	public ReimbursementController() {
		this.authorizationService = new AuthorizationService(); //instantiate
		this.reimbursementService = new ReimbursementService();
	}
	
	private Handler getAllReimbursements = (ctx) -> {
		//must be FM or employee to access this endpoint
		User currentLoggedIn = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authorizationService.authroizeEmployeeAndFinanceManager(currentLoggedIn);
		
		List<Reimbursement> reimbursements = this.reimbursementService.getAllReimbursements(currentLoggedIn);
		
		ctx.json(reimbursements);
	};
	
	//only FM
	private Handler resolveReimbursement = (ctx) -> {
		User currentLoggedIn = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authorizationService.authorizeFinanceManager(currentLoggedIn);

		String reimbursementId = ctx.pathParam("reimb_id");
		ChangeReimbursementStatusDTO dto = ctx.bodyAsClass(ChangeReimbursementStatusDTO.class);
		
		this.reimbursementService.resolveReimbursement(currentLoggedIn, reimbursementId,dto.getStatus());
		
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		// TODO Auto-generated method stub
		
		app.get("/reimbursements", getAllReimbursements);
		app.patch("/reimbursements/{reimb_id}/status", resolveReimbursement);
	}

} 
