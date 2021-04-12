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
public class Question {
	private int quesId;
	private String quesCont;
	private String creatDate;
	private String subId;
	private boolean status;

	public Question() {
	}

	public Question(int quesId, String quesCont, String creatDate, String subId, boolean status) {
		this.quesId = quesId;
		this.quesCont = quesCont;
		this.creatDate = creatDate;
		this.subId = subId;
		this.status = status;
	}

	public int getQuesId() {
		return quesId;
	}

	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}

	public String getQuesCont() {
		return quesCont;
	}

	public void setQuesCont(String quesCont) {
		this.quesCont = quesCont;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
