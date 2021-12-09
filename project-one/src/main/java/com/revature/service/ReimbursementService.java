package com.revature.service;


import java.io.InputStream;
import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import com.revature.dao.ReimbursementDAO;
import com.revature.exception.ReceiptNotFoundException;
import com.revature.exception.ReimbursementAlreadyResolvedException;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.exception.UnauthorizedException;
import com.revature.model.Reimbursement;
import com.revature.model.User;

import io.javalin.http.Context;

public class ReimbursementService {

	private ReimbursementDAO reimbursementDao;
	
	public ReimbursementService() {
		this.reimbursementDao = new ReimbursementDAO();
	}
	
	public List<Reimbursement> getAllReimbursements(User currentLoggedIn) throws SQLException {
		List<Reimbursement> listOfReimbursements = null; 
		
		if(currentLoggedIn.getUser_role().equals("Finance Manager")) {
			listOfReimbursements = this.reimbursementDao.getAllReimbursements();
			
		}else if(currentLoggedIn.getUser_role().equals("Employee")) {
			listOfReimbursements = this.reimbursementDao.getAllReimbursementsByEmployee(currentLoggedIn.getUser_id());
			
			
		} return listOfReimbursements;
	}


	//by reimbAuthor == employee->the one who submitted the reimbursement
	public List<Reimbursement> getAllReimbursementsByEmployee(User currentLoggedIn, String reimbursementAuthor) throws SQLException {

		List<Reimbursement> listOfReimbursements = null;

		int id = Integer.parseInt(reimbursementAuthor);
		if(currentLoggedIn.getUser_role().equals("Finance Manager") || currentLoggedIn.getUser_role().equals("Employee")) {
			listOfReimbursements = this.reimbursementDao.getAllReimbursementsByEmployee(currentLoggedIn.getUser_id());
			
		}

		return listOfReimbursements;
	}
	
	
	
	//by reimbId
	public Reimbursement getReimbursementById(User currentLoggedIn, String reimbursementId)
			throws SQLException, InvalidParameterException, ReimbursementNotFoundException {

		try {
			int id = Integer.parseInt(reimbursementId);

			Reimbursement r = this.reimbursementDao.getReimbursementById(id);

			if (r == null) {
				throw new ReimbursementNotFoundException("Reimbursement with an id of " + id + " was not found");
			}
			return r;
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int convertable value");
		}
	}
	
	
	public Reimbursement updateReimbursement(User currentLoggedIn, String reimbursementId, String status
			) throws SQLException, ReimbursementNotFoundException, ReimbursementAlreadyResolvedException {
	
		try {
			//use hashset= every item is unique
			Set<String> reimbursementStatus = new HashSet<>();
			
			reimbursementStatus.add("Pending");
			reimbursementStatus.add("Approved");
			reimbursementStatus.add("Denied");
			if (!(reimbursementStatus.contains(status))) {
				throw new InvalidParameterException("Reimbursement cannot be null! Must have a status of pending, approved, or denied.");
			}
			
			int id = Integer.parseInt(reimbursementId);
//			int resolver = Integer.parseInt(reimbursementResolver);
			Reimbursement reimbursement = this.reimbursementDao.getReimbursementById(id);

			if(reimbursement == null) { //check is reimbursement exists or not
				throw new ReimbursementNotFoundException ("Reimbursement with an id of " + id + " was not found");
			}
			if(reimbursement.getReimb_resolver() != 0) { //if !=0, reimb alr resolved
				throw new ReimbursementAlreadyResolvedException("Reimbursement has already been resolved");
			}else { //==0 
				return this.reimbursementDao.updateReimbursement(currentLoggedIn.getUser_id(), id, status);
			}
			}catch (NumberFormatException e) {
			throw new InvalidParameterException("Reimbursement id MUST be an int");
		
			
		}
			
 

}
//check if mimetype is pdf,jpeg,or png -> application/pdf, image/jpeg, image/png
//if not throw invalidParameter Exception
	public Reimbursement addReimbursement(User currentLoggedIn, String mimeType, String reimbursementType,
			String reimbursementAmount, String reimbursementDescription, InputStream content) throws SQLException {
	
		double submitAmount = Double.parseDouble(reimbursementAmount);
		Set<String> allowedFileTypes = new HashSet<>();
		
		allowedFileTypes.add("image/jpeg");
		allowedFileTypes.add("image/png");
		
		if(!allowedFileTypes.contains(mimeType)) {
			throw new InvalidParameterException("This type of file is not accepted for receipt; only JPEG or PNG are allowed");
		}
	//author, type, amt, description, content of the file(bytes)
		int reimbursementAuthor = currentLoggedIn.getUser_id(); //whoever is logged in and adding new reimb
	
		return this.reimbursementDao.addReimbursement(reimbursementAuthor, reimbursementType, submitAmount, 
				reimbursementDescription, content);
		
	
	
	
	}

//FM can see receipt images
	//EM can only view their own receipt images
	public InputStream getReceiptImageById(User currentLoggedIn, String reimbursementId) throws SQLException, UnauthorizedException, ReceiptNotFoundException {
		try {
			 int id = Integer.parseInt(reimbursementId);
			
			//grabs employees receipts
			if(currentLoggedIn.getUser_role().equals("Employee")) {
			int reimbursementAuthor = currentLoggedIn.getUser_id();
			List<Reimbursement> reimbursementsOfEmployee = this.reimbursementDao.getAllReimbursementsByEmployee(reimbursementAuthor);
				
			Set<Integer> reimbursementIdsEncountered = new HashSet<>();
			for(Reimbursement r : reimbursementsOfEmployee) {
				reimbursementIdsEncountered.add(r.getReimb_author());
				
			}
		
			if(!reimbursementIdsEncountered.contains(reimbursementAuthor)) {
				throw new UnauthorizedException("You cannot access any receipts that are not yours!");
			}
		} 
			
			InputStream receipt = this.reimbursementDao.getReceiptImageById(id);
			
			if(receipt == null) {
				throw new ReceiptNotFoundException("Receipt was not found. Check if receipt exists and try again");
			}
			return receipt;
			
			
			
			} catch(NumberFormatException e) {
			throw new InvalidParameterException("Reimbursement id must be an int");
		}
	

		

			
}
	}
	
