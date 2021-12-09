package com.revature.model;


import java.io.InputStream;
import java.util.Objects;


public class Reimbursement {

	
	private int reimb_id;
	private double reimb_amount;
	private String reimb_submitted;
	private String reimb_resolved;
	private String status;
	private String type;
	private String description;
	private int reimb_author;
	private int reimb_resolver;


public Reimbursement () {
	
}

public Reimbursement(int reimb_id, double reimb_amount, String reimb_submitted, String reimb_resolved, String status,
		String type, String description, int reimb_author, int reimb_resolver) {
	super();
	this.reimb_id = reimb_id;
	this.reimb_amount = reimb_amount;
	this.reimb_submitted = reimb_submitted;
	this.reimb_resolved = reimb_resolved;
	this.status = status;
	this.type = type;
	this.description = description;
	this.reimb_author = reimb_author;
	this.reimb_resolver = reimb_resolver;
	
	
}
public Reimbursement (int reimb_resolver, int reimb_id, String status, String reimb_resolved) {
	this.reimb_resolver = reimb_resolver;
	this.reimb_id = reimb_id;
	this.status = status;
	this.reimb_resolved = reimb_resolved;
	
}
public int getReimb_id() {
	return reimb_id;
}

public void setReimb_id(int reimb_id) {
	this.reimb_id = reimb_id;
}

public double getReimb_amount() {
	return reimb_amount;
}

public void setReimb_amount(double reimb_amount) {
	this.reimb_amount = reimb_amount;
}

public String getReimb_submitted() {
	return reimb_submitted;
}

public void setReimb_submitted(String reimb_submitted) {
	this.reimb_submitted = reimb_submitted;
}

public String getReimb_resolved() {
	return reimb_resolved;
}

public void setReimb_resolved(String reimb_resolved) {
	this.reimb_resolved = reimb_resolved;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public int getReimb_author() {
	return reimb_author;
}

public void setReimb_author(int reimb_author) {
	this.reimb_author = reimb_author;
}

public int getReimb_resolver() {
	return reimb_resolver;
}

public void setReimb_resolver(int reimb_resolver) {
	this.reimb_resolver = reimb_resolver;
}


@Override
public int hashCode() {
	return Objects.hash(description, reimb_amount, reimb_author, reimb_id, reimb_resolved, reimb_resolver,
			reimb_submitted, status, type);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Reimbursement other = (Reimbursement) obj;
	return Objects.equals(description, other.description)
			&& Double.doubleToLongBits(reimb_amount) == Double.doubleToLongBits(other.reimb_amount)
			&& reimb_author == other.reimb_author && reimb_id == other.reimb_id
			&& Objects.equals(reimb_resolved, other.reimb_resolved) && reimb_resolver == other.reimb_resolver
			&& Objects.equals(reimb_submitted, other.reimb_submitted) && Objects.equals(status, other.status)
			&& Objects.equals(type, other.type);
}

@Override
public String toString() {
	return "Reimbursement [reimb_id=" + reimb_id + ", reimb_amount=" + reimb_amount + ", reimb_submitted="
			+ reimb_submitted + ", reimb_resolved=" + reimb_resolved + ", status=" + status + ", type=" + type
			+ ", description=" + description + ", reimb_author=" + reimb_author + ", reimb_resolver=" + reimb_resolver
			+ "]";
}
















}






