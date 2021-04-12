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
public class History {
	private String subId;
	private String makeQuizDate;
	private float mark;

	public History() {
	}

	public History(String subId, String makeQuizDate, float mark) {
		this.subId = subId;
		this.makeQuizDate = makeQuizDate;
		this.mark = mark;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getMakeQuizDate() {
		return makeQuizDate;
	}

	public void setMakeQuizDate(String makeQuizDate) {
		this.makeQuizDate = makeQuizDate;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}
	
	
}
