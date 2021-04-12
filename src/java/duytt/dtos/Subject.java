/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.dtos;

/**
 *
 * @author thant
 */
public class Subject {
	private String subId;
	private String subName;
	private int numQues;
	private int numTime;
	private boolean status;

	public Subject() {
	}

	public Subject(String subId, String subName, int numQues, int numTime, boolean status) {
		this.subId = subId;
		this.subName = subName;
		this.numQues = numQues;
		this.numTime = numTime;
		this.status = status;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public int getNumQues() {
		return numQues;
	}

	public void setNumQues(int numQues) {
		this.numQues = numQues;
	}

	public int getNumTime() {
		return numTime;
	}

	public void setNumTime(int numTime) {
		this.numTime = numTime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
	
}
