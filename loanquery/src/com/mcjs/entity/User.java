package com.mcjs.entity;

import javax.persistence.Table;


@SuppressWarnings("serial")
@Table(name="loan_user"
,catalog="loanquery"
)
public class User implements java.io.Serializable {
	private int user_id;
	private String user_name;
	private String user_passWord;
	private int user_role;
	private int user_status;
	private String user_roleName;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_passWord() {
		return user_passWord;
	}
	public void setUser_passWord(String user_passWord) {
		this.user_passWord = user_passWord;
	}
	public int getUser_role() {
		return user_role;
	}
	public void setUser_role(int user_role) {
		this.user_role = user_role;
	}
	public int getUser_status() {
		return user_status;
	}
	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}
	public String getUser_roleName() {
		return user_roleName;
	}
	public void setUser_roleName(String user_roleName) {
		this.user_roleName = user_roleName;
	}	
	
}
