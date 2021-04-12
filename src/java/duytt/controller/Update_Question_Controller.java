/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.controller;

import duytt.daos.AnswerDAO;
import duytt.daos.QuestionDAO;
import duytt.daos.SubjectDAO;
import duytt.dtos.Answer;
import duytt.dtos.Question;
import duytt.dtos.Subject;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thant
 */
public class Update_Question_Controller extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private final static String HOMEADIM = "HomeAdmin_Page_Controller";

	private boolean checkQuesIdInList(int quesId) throws ClassNotFoundException, SQLException {
		List<Question> listQues = new QuestionDAO().getQuestion();
		for (Question listQue : listQues) {
			if (listQue.getQuesId() == quesId) {
				return true;
			}
		}
		return false;
	}

	private boolean checkAnsIdInList(int ansId) throws ClassNotFoundException, SQLException {
		List<Answer> listAns = new AnswerDAO().getAnswer();
		for (Answer ans : listAns) {
			if (ans.getAnsId() == ansId) {
				return true;
			}
		}
		return false;
	}
	private boolean checkSubIdInList(String subId) throws ClassNotFoundException, SQLException {
		List<Subject> listSub = new SubjectDAO().getSubject();
		for (Subject subject : listSub) {
			if(subject.getSubId().equals(subId))
				return true;
		}
		return false;
	}

	private boolean checkRightEqAns(List<String> list, int ansId) throws ClassNotFoundException, SQLException {
		for (String string : list) {
			if (Integer.parseInt(string) == ansId) {
				return true;
			}
		}
		return false;
	}

	private String getDateByQuesId(int quesId) throws ClassNotFoundException, SQLException {
		List<Question> listQues = new QuestionDAO().getQuestion();
		for (Question listQue : listQues) {
			if (listQue.getQuesId() == quesId) {
				return listQue.getCreatDate();
			}
		}
		return null;
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String url=HOMEADIM;
		try {
			HttpSession session = request.getSession();
			session.setAttribute("SUCCESS", null);
			url=request.getParameter("controller");
			
			String txtQuesId = request.getParameter("txtQuesId");
			String txtQuesContent = request.getParameter("txtQuesContent");		
			List<String> list = new ArrayList<>();
			List<Answer> listAns = new ArrayList<>();
			String ans1Id = request.getParameter("ansD[1]");
			String ans1Con = request.getParameter("ansC[1]");
			String ans2Id = request.getParameter("ansD[2]");
			String ans2Con = request.getParameter("ansC[2]");
			String ans3Id = request.getParameter("ansD[3]");
			String ans3Con = request.getParameter("ansC[3]");
			String ans4Id = request.getParameter("ansD[4]");
			String ans4Con = request.getParameter("ansC[4]");
			String txtCheckBox = request.getParameter("txtCheckBox");
			String cbxSubId = request.getParameter("cbxSub");
			String rightAns = "";
			boolean checkBox = false;
			for (int i = 1; i < 21; i++) {
				String rTmp = "rightAns[" + i + "]";
				String tmp = request.getParameter(rTmp);
				if (tmp != null) {
					rightAns = tmp;
					break;
				}
			}
			if (txtCheckBox != null) {
				if (txtCheckBox.equals("on")) {
					checkBox = true;
				}
			}
			
			boolean check = true;
			if(cbxSubId.equals(""))
			{
				check = false;
				request.setAttribute("CBX_ERROR", "Please choose SUBJECT again");
			}else if(!checkSubIdInList(cbxSubId))
			{
				check = false;
				request.setAttribute("CBX_ERROR", "Please choose SUBJECT again");
			}
			if (rightAns.isEmpty() || rightAns.equals("")) {
				check = false;
				request.setAttribute("RIGHTANS_ERROR", "Please Check Again Right Answer");
			}
			if (!txtQuesId.matches("[0-9]+")) {
				check = false;
				request.setAttribute("QUESID_ERROR", "Please Check Again");
			} else if (!checkQuesIdInList(Integer.parseInt(txtQuesId))) {
				check = false;
				request.setAttribute("QUESID_ERROR", "Please Check Again");
			}
			if (txtQuesContent.isEmpty() || txtQuesContent.equals("")) {
				check = false;
				request.setAttribute("QUESC_ERROR", "Question Content not empty");
			}

			if (!ans1Id.matches("[0-9]+")) {
				check = false;
				request.setAttribute("QUESID_ERROR", "Please Check Answer Again");
			} else if (!checkAnsIdInList(Integer.parseInt(ans1Id))) {
				check = false;
				request.setAttribute("QUESID_ERROR", "Please Check Answer Again");
			} else {
				list.add(ans1Id);
				if (!rightAns.equals("")) {
					Answer answer = null;
					if (rightAns.equals(ans1Id)) {
						answer = new Answer(Integer.parseInt(ans1Id), ans1Con, true, Integer.parseInt(txtQuesId), true);
					} else {
						answer = new Answer(Integer.parseInt(ans1Id), ans1Con, false, Integer.parseInt(txtQuesId), true);
					}
					if (answer != null) {
						listAns.add(answer);
					}
				}
			}
			if (ans1Con.isEmpty() || ans1Con.equals("")) {
				check = false;
				request.setAttribute("ANSC_ERROR", "Answer Content not empty");
			}
			if (!ans2Id.matches("[0-9]+")) {
				check = false;
				request.setAttribute("QUESID_ERROR", "Please Check Answer Again");
			} else if (!checkAnsIdInList(Integer.parseInt(ans2Id))) {
				check = false;
				request.setAttribute("QUESID_ERROR", "Please Check Answer Again");
			} else {
				list.add(ans2Id);
				if (!rightAns.equals("")) {
					Answer answer = null;
					if (rightAns.equals(ans2Id)) {
						answer = new Answer(Integer.parseInt(ans2Id), ans2Con, true, Integer.parseInt(txtQuesId), true);
					} else {
						answer = new Answer(Integer.parseInt(ans2Id), ans2Con, false, Integer.parseInt(txtQuesId), true);
					}
					if (answer != null) {
						listAns.add(answer);
					}
				}
			}
			if (ans2Con.isEmpty() || ans2Con.equals("")) {
				check = false;
				request.setAttribute("ANSC_ERROR", "Answer Content not empty");
			}
			if (!ans3Id.matches("[0-9]+")) {
				check = false;
				request.setAttribute("QUESID_ERROR", "Please Check Answer Again");
			} else if (!checkAnsIdInList(Integer.parseInt(ans3Id))) {
				check = false;
				request.setAttribute("QUESID_ERROR", "Please Check Answer Again");
			} else {
				list.add(ans3Id);
				if (!rightAns.equals("")) {
					Answer answer = null;
					if (rightAns.equals(ans3Id)) {
						answer = new Answer(Integer.parseInt(ans3Id), ans3Con, true, Integer.parseInt(txtQuesId), true);
					} else {
						answer = new Answer(Integer.parseInt(ans3Id), ans3Con, false, Integer.parseInt(txtQuesId), true);
					}
					if (answer != null) {
						listAns.add(answer);
					}
				}
			}
			if (ans3Con.isEmpty() || ans3Con.equals("")) {
				check = false;
				request.setAttribute("ANSC_ERROR", "Answer Content not empty");
			}
			if (!ans4Id.matches("[0-9]+")) {
				check = false;
				request.setAttribute("QUESID_ERROR", "Please Check Answer Again");
			} else if (!checkAnsIdInList(Integer.parseInt(ans4Id))) {
				check = false;
				request.setAttribute("QUESID_ERROR", "Please Check Answer Again");
			} else {
				list.add(ans4Id);
				if (!rightAns.equals("")) {
					Answer answer = null;
					if (rightAns.equals(ans4Id)) {
						answer = new Answer(Integer.parseInt(ans4Id), ans4Con, true, Integer.parseInt(txtQuesId), true);
					} else {
						answer = new Answer(Integer.parseInt(ans4Id), ans4Con, false, Integer.parseInt(txtQuesId), true);
					}
					if (answer != null) {
						listAns.add(answer);
					}
				}
			}
			if (ans4Con.isEmpty() || ans4Con.equals("")) {
				check = false;
				request.setAttribute("ANSC_ERROR", "Answer Content not empty");
			}
			if (check) {
				boolean checkNext = true;
				if (!checkRightEqAns(list, Integer.parseInt(rightAns))) {
					checkNext = false;
					request.setAttribute("RIGHTANS_ERROR", "Please Check Again Right Answer");
				}
				if (checkNext) {
					String date=getDateByQuesId(Integer.parseInt(txtQuesId));
					Question quesT = new Question(Integer.parseInt(txtQuesId), txtQuesContent, date, cbxSubId, checkBox);
					new QuestionDAO().updateQuestion(quesT);
					for (Answer listAn : listAns) {
						new AnswerDAO().updateQuestion(listAn);
					}
				}
			}
		} catch (Exception e) {
			log("Update_Question_Controller: " + e.toString());
		} finally {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
