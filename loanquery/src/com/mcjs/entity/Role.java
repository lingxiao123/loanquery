package com.mcjs.entity;

import javax.persistence.Table;

@SuppressWarnings("serial")
@Table(name="loan_role"
,catalog="loanquery"
)
public class Role implements java.io.Serializable {
	private int role_id;
	private String role_roleName;
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole_roleName() {
		return role_roleName;
	}
	public void setRole_roleName(String role_roleName) {
		this.role_roleName = role_roleName;
	}
}
