package com.mcjs.entity;

@SuppressWarnings("serial")
public class ImportData implements java.io.Serializable {
	private int im_id;
	private String im_imortNumber;
	private String im_type;
	private String im_addTime;
	private int im_status;
	public int getIm_id() {
		return im_id;
	}
	public void setIm_id(int im_id) {
		this.im_id = im_id;
	}
	public String getIm_imortNumber() {
		return im_imortNumber;
	}
	public void setIm_imortNumber(String im_imortNumber) {
		this.im_imortNumber = im_imortNumber;
	}
	public String getIm_type() {
		return im_type;
	}
	public void setIm_type(String im_type) {
		this.im_type = im_type;
	}
	public String getIm_addTime() {
		return im_addTime;
	}
	public void setIm_addTime(String im_addTime) {
		this.im_addTime = im_addTime;
	}
	public int getIm_status() {
		return im_status;
	}
	public void setIm_status(int im_status) {
		this.im_status = im_status;
	}
}
