package com.revature.dto;

import java.util.Objects;

public class ResolveReimbursementDTO {

	private String reimbStatus;
	private int reimbResolver;
	private String reimbResolved;
	private int reimbAuthor;
	
	public ResolveReimbursementDTO() {
		super();
	}

	public ResolveReimbursementDTO(String reimbStatus, int reimbResolver, String reimbResolved, int reimbAuthor) {
		super();
		this.reimbStatus = reimbStatus;
		this.reimbResolver = reimbResolver;
		this.reimbResolved = reimbResolved;
		this.reimbAuthor = reimbAuthor;
		
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public int getReimbResolver() {
		return reimbResolver;
	}

	public void setReimbResolver(int reimbResolver) {
		this.reimbResolver = reimbResolver;
	}

	public String getReimbResolved() {
		return reimbResolved;
	}

	public void setReimbResolved(String reimbResolved) {
		this.reimbResolved = reimbResolved;
	}

	public int getReimbAuthor() {
		return reimbAuthor;
	}

	public void setReimbAuthor(int reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reimbAuthor, reimbResolved, reimbResolver, reimbStatus);
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
		return reimbAuthor == other.reimbAuthor && Objects.equals(reimbResolved, other.reimbResolved)
				&& reimbResolver == other.reimbResolver && Objects.equals(reimbStatus, other.reimbStatus);
	}

	@Override
	public String toString() {
		return "ResolveReimbursementDTO [reimbStatus=" + reimbStatus + ", reimbResolver=" + reimbResolver
				+ ", reimbResolved=" + reimbResolved + ", reimbAuthor=" + reimbAuthor + "]";
	}

}
