package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.User;
import com.revature.util.JDBCUtility;

public class UserDAO {

	public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
		try(Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT * FROM project_one.users WHERE username = ? AND password = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			int user_id = rs.getInt("user_id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String email = rs.getString("email");
			String user = rs.getString("username");
			String pass = rs.getString("password");
			String user_role = rs.getString("user_role");
			
			return new User(user_id, first_name, last_name, email, user, pass, user_role);
		} else {
			return null;
		}
	}
}
}
