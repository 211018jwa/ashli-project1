package com.revature.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.tika.Tika;

import com.revature.dto.MessageDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.AuthorizationService;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;


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
	
	//employees should be able to view all of their reimbursements
		private Handler getAllReimbursementsByEmployee = (ctx) -> {
			User currentLoggedIn = (User) ctx.req.getSession().getAttribute("currentuser");
			this.authorizationService.authroizeEmployeeAndFinanceManager(currentLoggedIn);
			
			//String reimbursementId = ctx.pathParam("id");
			String reimbursementAuthor = ctx.pathParam("reimb_author");
			List<Reimbursement> reimbursements= this.reimbursementService.getAllReimbursementsByEmployee(currentLoggedIn, reimbursementAuthor);

			ctx.json(reimbursements);
		};
		
	//view ONE reimbursement by reimbId
	private Handler getReimbursementById = (ctx) -> {
		User currentLoggedIn = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authorizationService.authorizeFinanceManager(currentLoggedIn);
		
		String reimbursementId = ctx.pathParam("reimb_id");
		Reimbursement reimbursement = this.reimbursementService.getReimbursementById(currentLoggedIn, reimbursementId);
		
		ctx.json(reimbursement);
	};
	
	
	
	
	//only FM
	private Handler updateReimbursement = (ctx) -> {
		User currentLoggedIn = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authorizationService.authorizeFinanceManager(currentLoggedIn);

		String reimbursementId = ctx.pathParam("reimb_id");
		String reimbursementStatus = ctx.formParam("reimbursementStatus");
		String reimbursementResolved = ctx.formParam("reimbursementResolved");
//		String reimbursementResolver = ctx.pathParam("reimbursement resolver");
		System.out.println(reimbursementResolved);
		Reimbursement reimbursement = this.reimbursementService.updateReimbursement(currentLoggedIn, reimbursementId,
				reimbursementStatus);
		
		ctx.json(reimbursement);

		
	};
	private Handler addReimbursement = (ctx) -> {
		
		User currentLoggedIn = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authorizationService.authorizeEmployee(currentLoggedIn);
		
		String reimbursementType = ctx.formParam("reimbursementType");
		String reimbursementAmount   = ctx.formParam("reimbursementAmount");
		String reimbursementDescription = ctx.formParam("reimbursementDescription");
		
		//gets files from HTTP request
		UploadedFile file = ctx.uploadedFile("reimbursementReceipt");
		if (file == null) {
			ctx.status(400); //bad response, server cannot process
			ctx.json(new MessageDTO("You must upload a receipt- ONLY JPEG, or PNG"));
			return;
		}
		
		InputStream content = file.getContent(); //actual content of file
		
		
		Tika tika = new Tika();
		//disallow files that aren't jpeg,or png
		//image/jpeg
		//image/png
		String mimeType = tika.detect(content); //detects mimetype
//		System.out.println(mimeType);
		//service layer
		Reimbursement addedReimbursement = this.reimbursementService.addReimbursement(currentLoggedIn, mimeType, reimbursementType, 
				reimbursementAmount, reimbursementDescription, content);
		ctx.json(addedReimbursement);
		ctx.status(201); //content created
 	};

	
	private Handler getReceiptImageById = (ctx) -> {
		User currentLoggedIn = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authorizationService.authroizeEmployeeAndFinanceManager(currentLoggedIn);
		
		
		String reimbursementId = ctx.pathParam("reimb_id");
		InputStream receipt = this.reimbursementService.getReceiptImageById(currentLoggedIn, reimbursementId);
		
		Tika tika = new Tika();
		String mimeType = tika.detect(receipt);
		
		ctx.contentType(mimeType); //what type of content .. jpeg, png, etc
		ctx.result(receipt); //sending image to client
	};
	
		
	@Override
	public void mapEndpoints(Javalin app) {
		
		//FM
		app.get("/reimbursements", getAllReimbursements);
		app.put("/reimbursements/{reimb_id}/status", updateReimbursement);
		
		
		
		//EMPLOYEE (must be logged in as EM)
		app.get("/reimbursements/{reimb_author}", getAllReimbursementsByEmployee);
		app.post("/reimbursements", addReimbursement);
		app.get("/reimbursements/{reimb_id}/image", getReceiptImageById);
		
	}	
	

} 
