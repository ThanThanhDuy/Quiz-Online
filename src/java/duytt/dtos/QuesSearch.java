/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.dtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author thant
 */
public class QuesSearch {
	private String subId;
	HashMap<Integer, List<Answer>> has;

	public QuesSearch() {
	}

	public QuesSearch(String subId, HashMap<Integer, List<Answer>> has) {
		this.subId = subId;
		this.has = has;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public HashMap<Integer, List<Answer>> getHas() {
		return has;
	}

	public void setHas(HashMap<Integer, List<Answer>> has) {
		this.has = has;
	}
	
	
	public void add(int quesId, Answer ans) {
		if (has == null) {
			this.has = new HashMap<Integer, List<Answer>>();
		}
		if (has.containsKey(quesId)) {
			List<Answer> listTemp = has.get(quesId);
			listTemp.add(ans);
			has.put(quesId, listTemp);
		} else {
			List<Answer> listTemp = new ArrayList<>();
			listTemp.add(ans);
			has.put(quesId, listTemp);
		}
	}

	public void update(int quesId, Answer ans) {
		if (this.has != null) {
			if (this.has.containsKey(quesId)) {
				List<Answer> listTemp = has.get(quesId);
				listTemp.add(ans);
				this.has.replace(quesId, listTemp);
			}
		}
	}
		
}
