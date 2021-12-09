package com.revature.dao;



import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




import com.revature.model.Reimbursement;
import com.revature.util.JDBCUtility;

public class ReimbursementDAO {

	//gets ALL reimbursements in the system
	public List<Reimbursement> getAllReimbursements() throws SQLException {
		try(Connection con = JDBCUtility.getConnection()) { //calls method from JDBCUtil
			List<Reimbursement> listOfReimbursements = new ArrayList<>();
			String sql = "SELECT reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_status, reimb_type, reimb_description, reimb_author, reimb_resolver FROM project_one.reimbursements";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				int reimb_id = rs.getInt("reimb_id");
				int reimb_amount = rs.getInt("reimb_amount");
				String reimb_submitted = rs.getString("reimb_submitted");
				String reimb_resolved = rs.getString("reimb_resolved");
				String reimb_status = rs.getString("reimb_status");
				String reimb_type = rs.getString("reimb_type");
				String reimb_description = rs.getString("reimb_description");
				int reimb_author = rs.getInt("reimb_author");
				int reimb_resolver = rs.getInt("reimb_resolver");
				
			
					
			Reimbursement r1 = new Reimbursement(reimb_id, reimb_amount, reimb_submitted, reimb_resolved, 
					reimb_status, reimb_type, reimb_description, 
					reimb_author, reimb_resolver);
			
			listOfReimbursements.add(r1);	//adds reimbursements to the list
				
			}
			
			
			
			return listOfReimbursements;
			
	}
		
}
	
	
	//getting all reimbs by that specific employee(reimb_auth, user_id)
	public List<Reimbursement> getAllReimbursementsByEmployee(int Id) throws SQLException {
		try(Connection con = JDBCUtility.getConnection()) {
			List<Reimbursement> reimbursements = new ArrayList<>();	
			String sql = "SELECT reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_status, reimb_type, reimb_description, reimb_author, reimb_resolver FROM project_one.reimbursements WHERE reimb_author = ?";
					
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Id);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				int reimb_id = rs.getInt("reimb_id");
				int reimb_amount = rs.getInt("reimb_amount");
				String reimb_submitted = rs.getString("reimb_submitted");
				String reimb_resolved = rs.getString("reimb_resolved");
				String reimb_status = rs.getString("reimb_status");
				String reimb_type = rs.getString("reimb_type");
				String reimb_description = rs.getString("reimb_description");
				int reimb_author = rs.getInt("reimb_author");
				int reimb_resolver = rs.getInt("reimb_resolver");
				
				
					
				Reimbursement r1 = new Reimbursement(reimb_id, reimb_amount, reimb_submitted, reimb_resolved, 
						reimb_status, reimb_type, reimb_description,
						reimb_author, reimb_resolver);
			reimbursements.add(r1);	//adds reimbursements to the list
			
			}
			
			
			
			return reimbursements;
			
	}
}
	
	//gets reimbursement by id#
	public Reimbursement getReimbursementById(int id) throws SQLException {
		try (Connection c = JDBCUtility.getConnection()) {
			String sql = "SELECT reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_status, reimb_type, reimb_description, reimb_author, reimb_resolver "
					+ "FROM project_one.reimbursements "
					+ "WHERE reimb_id = ?";

			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, id); // PASSES THE VALUE OF THE ID VARIABLE INTO THE FIRST QUESTION MARK

			ResultSet rs = pstmt.executeQuery(); //select, insert for rs

			 if (rs.next()) {
				 return new Reimbursement(rs.getInt("reimb_id"),
				rs.getInt("reimb_amount"),
				rs.getString("reimb_submitted"),
				rs.getString("reimb_resolved"),
				rs.getString("reimb_status"),
				rs.getString("reimb_type"),
				rs.getString("reimb_description"),
				rs.getInt("reimb_author"),
				rs.getInt("reimb_resolver"));
				
		}else {
			return null;
		}
	}catch(SQLException e) {
		throw new SQLException("Request cannot be completed");
	}
		
	}		
	
//resolves the reimb
	public Reimbursement updateReimbursement(int id, int reimbursementId, String reimbursementStatus) 
		 throws SQLException {
		try(Connection con = JDBCUtility.getConnection()) {
			
			String sql = "UPDATE project_one.reimbursements "
					+ "SET "
					+ "reimb_resolved = now(), "
					+ "reimb_status = ?, "
					+ "reimb_resolver = ?"
					+ "WHERE reimb_id = ?; ";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			
			//what time it was resolved
			//pstmt.setString(1, reimbursementResolved);
			//what's the status
			pstmt.setString(1, reimbursementStatus);
			//who resolved it .. resolver id and/or user id
			pstmt.setInt(2, id);
			//which reimbursement was changed
			pstmt.setInt(3, reimbursementId);
			
			pstmt.executeUpdate();
			Reimbursement r = getReimbursementById(reimbursementId); //
			
			//return new Reimbursement(id, reimbursementId, reimbursementStatus, r.getReimb_resolved()); //matches in reimbModel
			return new Reimbursement(reimbursementId, r.getReimb_amount(), r.getReimb_submitted(), 
					r.getReimb_resolved(), reimbursementStatus, r.getType(), 
					r.getDescription(), r.getReimb_author(), id);
		}
		

	
	}



	public Reimbursement addReimbursement(int reimbursementAuthor, String reimbursementType, double reimbursementAmount,
			String reimbursementDescription, InputStream receipt)  throws SQLException {
		try(Connection con = JDBCUtility.getConnection()) {
			con.setAutoCommit(false); 
			
			String sql = "INSERT INTO project_one.reimbursements (reimb_author, reimb_type, reimb_amount, reimb_submitted, reimb_description, reimb_receipt)"
						+ " VALUES (?,?,?,now(),?,?);";
		
			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,  reimbursementAuthor);
			pstmt.setString(2, reimbursementType);
			pstmt.setDouble(3, reimbursementAmount);
			pstmt.setString(4, reimbursementDescription);
			pstmt.setBinaryStream(5, receipt);
			
			int numberOfInsertedRecords = pstmt.executeUpdate();
			if(numberOfInsertedRecords != 1) {
				throw new SQLException("An issue has occured when adding reimbursement");
			}
		
			ResultSet rs = pstmt.getGeneratedKeys();
			
			rs.next(); 
			int generatedId = rs.getInt(1);
			
			con.commit(); //COMMIT
			
			return new Reimbursement(generatedId, reimbursementAmount,reimbursementDescription, rs.getString("reimb_submitted"), null,
					rs.getString("reimb_status"), reimbursementType, rs.getInt("reimb_id"), reimbursementAuthor);
		} 
		
	
}


	public InputStream getReceiptImageById(int reimbursementId) throws SQLException {
		try(Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT reimb_receipt FROM project_one.reimbursements WHERE reimb_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
					
			pstmt.setInt(1, reimbursementId);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				InputStream receipt = rs.getBinaryStream("reimb_receipt");
				return receipt;
			}
					
			return null;
		}
		
	}


	
	
}
