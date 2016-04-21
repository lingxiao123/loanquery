package com.mcjs.entity;


@SuppressWarnings("serial")
public class Authorizes implements java.io.Serializable {
	private String roleid;
	private String qxs;
	
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getQxs() {
		return qxs;
	}
	public void setQxs(String qxs) {
		this.qxs = qxs;
	}
}
