package com.mcjs.entity;

@SuppressWarnings("serial")
public class RoleAuthorize implements java.io.Serializable {
	private int ra_id;
	private String ra_roleid;
	private String ra_purviewname;
	public int getRa_id() {
		return ra_id;
	}
	public void setRa_id(int ra_id) {
		this.ra_id = ra_id;
	}
	public String getRa_roleid() {
		return ra_roleid;
	}
	public void setRa_roleid(String ra_roleid) {
		this.ra_roleid = ra_roleid;
	}
	public String getRa_purviewname() {
		return ra_purviewname;
	}
	public void setRa_purviewname(String ra_purviewname) {
		this.ra_purviewname = ra_purviewname;
	}
	
}
