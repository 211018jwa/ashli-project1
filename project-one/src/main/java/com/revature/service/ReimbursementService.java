package com.revature.service;


import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.exception.ReimbursementAlreadyResolvedException;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class ReimbursementService {

	private ReimbursementDAO reimbursementDao;
	
	public ReimbursementService() {
		this.reimbursementDao = new ReimbursementDAO();
	}
	
	public List<Reimbursement> getAllReimbursements(User currentLoggedIn) throws SQLException {
		List<Reimbursement> listOfReimbursements = null; 
		
		if(currentLoggedIn.getUserRole().equals("Finance Manager")) {
			listOfReimbursements = this.reimbursementDao.getAllReimbursements();
			
		}else if(currentLoggedIn.getUserRole().equals("Employee")) {
			listOfReimbursements = this.reimbursementDao.getReimbursementByEmployeeId(currentLoggedIn.getUserId());
			
			
		} return listOfReimbursements;
	}


	public void resolveReimbursement(User currentLoggedIn, String reimbursementResolved, String reimbursementResolver, String reimbursementStatus) throws SQLException, ReimbursementNotFoundException {
	
		try {
			int id = Integer.parseInt(reimbursementResolver);

			Reimbursement reimbursement = this.reimbursementDao.getReimbursementByAuthor(reimbursementResolver);

			if(reimbursement == null) { //check is reimbursement exists or not
				throw new ReimbursementNotFoundException ("Reimbursement with an id of " + reimbursementResolver + " was not found");
				
			if(reimbursement.getReimbId() == 0) { //if 0, no author for reimbursement yet
				this.reimbursementDao.resolveReimbursement(reimbursementResolver, reimbursementStatus, reimbursementResolved, currentLoggedIn.getUserId());
			}else { //if already updated status
				throw new ReimbursementAlreadyResolvedException("Reimbursement has already been resolved! You cannot make any other changes");
			}
				
			}
				
				
		}catch (NumberFormatException e) {
			throw new InvalidParameterException("Reimbursement id MUST be an int");
		}

		

	
	
			
}
}
	