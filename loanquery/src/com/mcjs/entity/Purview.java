package com.mcjs.entity;

@SuppressWarnings("serial")
public class Purview implements java.io.Serializable {
	private int p_id;
	private String p_modulename;
	private String p_menuname;
	private String p_menuurl;
	private String p_btntype;
	private String p_type;
	public String getP_btntype() {
		return p_btntype;
	}
	public void setP_btntype(String p_btntype) {
		this.p_btntype = p_btntype;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_modulename() {
		return p_modulename;
	}
	public void setP_modulename(String p_modulename) {
		this.p_modulename = p_modulename;
	}
	public String getP_menuname() {
		return p_menuname;
	}
	public void setP_menuname(String p_menuname) {
		this.p_menuname = p_menuname;
	}
	public String getP_menuurl() {
		return p_menuurl;
	}
	public void setP_menuurl(String p_menuurl) {
		this.p_menuurl = p_menuurl;
	}
	
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
}
