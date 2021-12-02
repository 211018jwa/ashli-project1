package com.revature.model;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;


public class Reimbursement {

	
	private int reimbId;
	private double reimbAmount;
	private String reimbSubmitted;
	private String reimbResolved;
	private String status;
	private String type;
	private String description;
	private Blob reimbReceipt;
	private int reimbAuthor;
	private int reimbResolver;


public Reimbursement () {
	
}

public Reimbursement(int reimbId, double reimbAmount, String reimbSubmitted, String reimbResolved, String status,
		String type, String description, Blob reimbReceipt, int reimbAuthor, int reimbResolver) {
	super();
	this.reimbId = reimbId;
	this.reimbAmount = reimbAmount;
	this.reimbSubmitted = reimbSubmitted;
	this.reimbResolved = reimbResolved;
	this.status = status;
	this.type = type;
	this.description = description;
	this.reimbReceipt = reimbReceipt;
	this.reimbAuthor = reimbAuthor;
	this.reimbResolver = reimbResolver;
	
}


public int getReimbId() {
	return reimbId;
}

public void setReimbId(int reimbId) {
	this.reimbId = reimbId;
}

public double getReimbAmount() {
	return reimbAmount;
}

public void setReimbAmount(double reimbAmount) {
	this.reimbAmount = reimbAmount;
}

public String getReimbSubmitted() {
	return reimbSubmitted;
}

public void setReimbSubmitted(String reimbSubmitted) {
	this.reimbSubmitted = reimbSubmitted;
}

public String getReimbResolved() {
	return reimbResolved;
}

public void setReimbResolved(String reimbResolved) {
	this.reimbResolved = reimbResolved;
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

public Blob getReimbReceipt() {
	return reimbReceipt;
}

public void setReimbReceipt(Blob reimbReceipt) {
	this.reimbReceipt = reimbReceipt;
}

public int getReimbAuthor() {
	return reimbAuthor;
}

public void setReimbAuthor(int reimbAuthor) {
	this.reimbAuthor = reimbAuthor;
}

public int getReimbResolver() {
	return reimbResolver;
}

public void setReimbResolver(int reimbResolver) {
	this.reimbResolver = reimbResolver;
}


@Override
public int hashCode() {
	return Objects.hash(description, reimbAmount, reimbAuthor, reimbId, reimbReceipt, reimbResolved, reimbResolver,
			reimbSubmitted, status, type);
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
			&& Double.doubleToLongBits(reimbAmount) == Double.doubleToLongBits(other.reimbAmount)
			&& reimbAuthor == other.reimbAuthor && reimbId == other.reimbId
			&& Objects.equals(reimbReceipt, other.reimbReceipt) && Objects.equals(reimbResolved, other.reimbResolved)
			&& reimbResolver == other.reimbResolver && Objects.equals(reimbSubmitted, other.reimbSubmitted)
			&& Objects.equals(status, other.status) && Objects.equals(type, other.type);
}

@Override
public String toString() {
	return "Reimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmitted=" + reimbSubmitted
			+ ", reimbResolved=" + reimbResolved + ", status=" + status + ", type=" + type + ", description="
			+ description + ", reimbReceipt=" + reimbReceipt + ", reimbAuthor=" + reimbAuthor + ", reimbResolver="
			+ reimbResolver + "]";
}

}