package com.revature.dto;

import java.util.Objects;

public class ResolveReimbursementDTO {

	private String reimb_status;
	private int reimb_resolver;
	private String reimb_resolved;
	private int reimb_author;
	
	public ResolveReimbursementDTO() {
		super();
	}

	public ResolveReimbursementDTO(String reimb_status, int reimb_resolver, String reimb_resolved, int reimb_author) {
		super();
		this.reimb_status = reimb_status;
		this.reimb_resolver = reimb_resolver;
		this.reimb_resolved = reimb_resolved;
		this.reimb_author = reimb_author;
		
	}

	public String getReimb_status() {
		return reimb_status;
	}

	public void setReimb_status(String reimb_status) {
		this.reimb_status = reimb_status;
	}

	public int getReimb_resolver() {
		return reimb_resolver;
	}

	public void setReimb_resolver(int reimb_resolver) {
		this.reimb_resolver = reimb_resolver;
	}

	public String getReimb_resolved() {
		return reimb_resolved;
	}

	public void setReimb_resolved(String reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}

	public int getReimb_author() {
		return reimb_author;
	}

	public void setReimb_author(int reimb_author) {
		this.reimb_author = reimb_author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reimb_author, reimb_resolved, reimb_resolver, reimb_status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResolveReimbursementDTO other = (ResolveReimbursementDTO) obj;
		return reimb_author == other.reimb_author && Objects.equals(reimb_resolved, other.reimb_resolved)
				&& reimb_resolver == other.reimb_resolver && Objects.equals(reimb_status, other.reimb_status);
	}

	@Override
	public String toString() {
		return "ResolveReimbursementDTO [reimb_status=" + reimb_status + ", reimb_resolver=" + reimb_resolver
				+ ", reimb_resolved=" + reimb_resolved + ", reimb_author=" + reimb_author + "]";
	}

	
}
