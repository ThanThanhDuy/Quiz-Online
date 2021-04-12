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
public class Create_Ques_Controller extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private boolean checkSub(String id) throws ClassNotFoundException, SQLException {
		List<Subject> list = new SubjectDAO().getSubject();
		for (Subject subject : list) {
			if (subject.getSubId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	private final static String ERROR = "CreateQues_Page_Controller";
	private final static String SUCCESS = "CreateQues_Page_Controller";

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String url = ERROR;
		try {
			HttpSession session = request.getSession();
			session.setAttribute("SUCCESS", null);
			String txtQues = request.getParameter("txtQues");
			String txtAnswer1 = request.getParameter("txtAnswer1");
			String txtAnswer2 = request.getParameter("txtAnswer2");
			String txtAnswer3 = request.getParameter("txtAnswer3");
			String txtAnswer4 = request.getParameter("txtAnswer4");
			String rightAnswer = request.getParameter("rightAnswer");
			String txtsubId = request.getParameter("txtsubId");
			boolean check = true;
			if (txtQues.isEmpty() || txtQues == null || txtQues.equals("")) {
				check = false;
				request.setAttribute("txtQues_Error", "Question not empty");
			} else if (txtQues.length() > 8000) {
				check = false;
				request.setAttribute("txtQues_Error", "Question length <= 8000");
			}

			if (txtsubId.isEmpty() || txtsubId == null || txtsubId.equals("")) {
				check = false;
				request.setAttribute("txtsubId_Error", "Please Choose Subject again");
			} else if (!checkSub(txtsubId)) {
				check = false;
				request.setAttribute("txtsubId_Error", "Please Choose Subject again");
			}
			if (rightAnswer.isEmpty() || rightAnswer == null || rightAnswer.equals("")) {
				check = false;
				request.setAttribute("rightAnswer_Error", "Please Choose right answer again");
			}
			if (txtAnswer1.isEmpty() || txtAnswer1 == null || txtAnswer1.equals("")) {
				check = false;
				request.setAttribute("txtAnswer1_Error", "Answer not empty");
			} else if (txtAnswer1.length() > 8000) {
				check = false;
				request.setAttribute("txtAnswer1_Error", "Answer length <= 8000");
			}
			if (txtAnswer2.isEmpty() || txtAnswer2 == null || txtAnswer2.equals("")) {
				check = false;
				request.setAttribute("txtAnswer2_Error", "Answer not empty");
			} else if (txtAnswer2.length() > 8000) {
				check = false;
				request.setAttribute("txtAnswer2_Error", "Answer length <= 8000");
			}
			if (txtAnswer3.isEmpty() || txtAnswer3 == null || txtAnswer3.equals("")) {
				check = false;
				request.setAttribute("txtAnswer3_Error", "Answer not empty");
			} else if (txtAnswer3.length() > 8000) {
				check = false;
				request.setAttribute("txtAnswer3_Error", "Answer length <= 8000");
			}
			if (txtAnswer4.isEmpty() || txtAnswer4 == null || txtAnswer4.equals("")) {
				check = false;
				request.setAttribute("txtAnswer4_Error", "Answer not empty");
			} else if (txtAnswer4.length() > 8000) {
				check = false;
				request.setAttribute("txtAnswer4_Error", "Answer length <= 8000");
			}
			if (check) {
				int quesId = new QuestionDAO().createQues(txtQues, txtsubId);
				txtAnswer1 += "`option1";
				txtAnswer2 += "`option2";
				txtAnswer3 += "`option3";
				txtAnswer4 += "`option4";
				List<String> listAns = new ArrayList<>();
				listAns.add(txtAnswer1);
				listAns.add(txtAnswer2);
				listAns.add(txtAnswer3);
				listAns.add(txtAnswer4);
				for (String listAn : listAns) {
					String[] ansTmp = listAn.split("`");
					Answer ans = new Answer();
					if (ansTmp[1].equals(rightAnswer)) {
						ans = new Answer(0, ansTmp[0], true, quesId, true);
					} else {
						ans = new Answer(0, ansTmp[0], false, quesId, true);
					}
					new AnswerDAO().createAnswer(ans);
				}
				url = SUCCESS;
			}
			
		} catch (Exception e) {
			log("Create_Ques_Controller: " + e.toString());
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
