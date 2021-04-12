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
public class Answer {

	private int ansId;
	private String ansContent;
	private boolean ansCorrect;
	private int quesId;
	private boolean status;

	public Answer() {
	}

	public Answer(int ansId, String ansContent, boolean ansCorrect, int quesId, boolean status) {
		this.ansId = ansId;
		this.ansContent = ansContent;
		this.ansCorrect = ansCorrect;
		this.quesId = quesId;
		this.status = status;
	}

	public int getAnsId() {
		return ansId;
	}

	public void setAnsId(int ansId) {
		this.ansId = ansId;
	}

	public String getAnsContent() {
		return ansContent;
	}

	public void setAnsContent(String ansContent) {
		this.ansContent = ansContent;
	}

	public boolean isAnsCorrect() {
		return ansCorrect;
	}

	public void setAnsCorrect(boolean ansCorrect) {
		this.ansCorrect = ansCorrect;
	}

	public int getQuesId() {
		return quesId;
	}

	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	
}
