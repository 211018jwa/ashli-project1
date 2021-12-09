package com.revature.model;

import java.util.Objects;

public class User {

	private int user_id;
	private String first_name;
	private String last_name;
	private String email;
	private String username;
	private String password;
	private String user_role;
	
	public User() {
		
	}
	public User(int user_id, String first_name, String last_name, String email, String username, String password, String user_role) {
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.user_role = user_role;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, first_name, last_name, password, user_id, user_role, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(first_name, other.first_name)
				&& Objects.equals(last_name, other.last_name) && Objects.equals(password, other.password)
				&& user_id == other.user_id && Objects.equals(user_role, other.user_role)
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", email="
				+ email + ", username=" + username + ", password=" + password + ", user_role=" + user_role + "]";
	}
	
}
