package com.revature.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.util.JDBCUtility;

public class ReimbursementDAO {

	
	public List<Reimbursement> getAllReimbursements() throws SQLException {
		try(Connection con = JDBCUtility.getConnection()) { //calls method from JDBCUtil
			List<Reimbursement> listOfReimbursements = new ArrayList<>();
			String sql = "SELECT * FROM project_one.reimbursements";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				int reimbId = rs.getInt("reimb_id");
				int reimbAmount = rs.getInt("reimb_amount");
				String reimbSubmitted = rs.getString("reimb_submitted");
				String reimbResolved = rs.getString("reimb_resolved");
				String reimbStatus = rs.getString("reimb_status");
				String reimbType = rs.getString("reimb_type");
				String reimbDescription = rs.getString("reimb_description");
				Blob reimbReceipt = rs.getBlob("reimb_receipt");
				int reimbAuthor = rs.getInt("reimb_author");
				int reimbResolver = rs.getInt("reimb_resolver");
			
					
			Reimbursement r1 = new Reimbursement(reimbId, reimbAmount, reimbSubmitted, reimbResolved, 
					reimbStatus, reimbType, reimbDescription, reimbReceipt,
					reimbAuthor, reimbResolver);
			
			listOfReimbursements.add(r1);	//adds reimbursements to the list
				
			}
			
			
			
			return listOfReimbursements;
			
	}
		
}
	
	
	
	public List<Reimbursement> getReimbursementByEmployeeId(int employeeId) throws SQLException {
		try(Connection con = JDBCUtility.getConnection()) {
			List<Reimbursement> reimbursements = new ArrayList<>();	
			String sql = "SELECT * FROM project_one.reimbursements WHERE user_id = ?";
					
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				int reimbId = rs.getInt("reimb_id");
				int reimbAmount = rs.getInt("reimb_amount");
				String reimbSubmitted = rs.getString("reimb_submitted");
				String reimbResolved = rs.getString("reimb_resolved");
				String reimbStatus = rs.getString("reimb_status");
				String reimbType = rs.getString("reimb_type");
				String reimbDescription = rs.getString("reimb_description");
				Blob reimbReceipt = rs.getBlob("reimb_receipt");
				int reimbAuthor = rs.getInt("reimb_author");
				int reimbResolver = rs.getInt("reimb_resolver");
				
					
			Reimbursement r1 = new Reimbursement(reimbId, reimbAmount, reimbSubmitted, reimbResolved, 
					reimbStatus, reimbType, reimbDescription, reimbReceipt,
					reimbAuthor, reimbResolver);
			reimbursements.add(r1);	//adds reimbursements to the list
			
			}
			
			
			
			return reimbursements;
			
	}
	}



	public Reimbursement getReimbursementByAuthor(String reimbursementAuthor) throws SQLException {
		try(Connection con = JDBCUtility.getConnection()) {
			
			String sql = "SELECT * FROM project_one.reimbursements WHERE reimb_author = ?";
					
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reimbursementAuthor);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
		
			if(rs.next()) {
				int reimbId = rs.getInt("reimb_id");
				int reimbAmount = rs.getInt("reimb_amount");
				String reimbSubmitted = rs.getString("reimb_submitted");
				String reimbResolved = rs.getString("reimb_resolved");
				String reimbStatus = rs.getString("reimb_status");
				String reimbType = rs.getString("reimb_type");
				String reimbDescription = rs.getString("reimb_description");
				Blob reimbReceipt = rs.getBlob("reimb_receipt");
				int reimbAuthor = rs.getInt("reimb_author");
				int reimbResolver = rs.getInt("reimb_resolver");
				
				return new Reimbursement(reimbId, reimbAmount, reimbSubmitted, reimbResolved, 
					reimbStatus, reimbType, reimbDescription, reimbReceipt,
					reimbAuthor, reimbResolver);
			} else {
				return null;
			}
			
					
			
			}
	}



	public void resolveReimbursement(String reimbursementResolver, String reimbursementStatus, String reimbursementResolved, int i) throws SQLException {
		try(Connection con = JDBCUtility.getConnection()) {
			
			String sql = "UPDATE reimbursements "
					+ "SET "
					+ "reimb_resolved = ?, "
					+ "reimb_status = ?, "
					+ "reimb_resolver = ?"
					+ "WHERE reimb_id = ?; ";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reimbursementResolver); //which reimbursement was changed
			pstmt.setString(2, reimbursementStatus); //what's the status 
			pstmt.setInt(3, reimbursementResolved); //who resolved it 
			pstmt.setString(4, i); //what time it was resolved
					
	}

	
	}



	public void changeStatus(String reimbursementResolver, String reimbursementStatus, String reimbursementResolved,
			int userId) {
		// TODO Auto-generated method stub
		
	}
}
