/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.controller;

import duytt.daos.QuestionDAO;
import duytt.daos.QuizDAO;
import duytt.daos.SubjectDAO;
import duytt.dtos.Quiz;
import duytt.dtos.User;
import java.io.IOException;
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
public class Finish_Quiz_Controller extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private final static String SUCCESS = "showMark.jsp";
	private final static String ERROR = "Quiz_Do_Controller";

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String url = ERROR;
		try {
			
			String subId = request.getParameter("subId");
			int quizTakeId = Integer.parseInt(request.getParameter("quizTakeId"));
			int quizId = Integer.parseInt(request.getParameter("quizId"));
			
			
			List<Quiz> listQuiz = new QuizDAO().getQuizAnsTmp(quizTakeId, quizId);	
		List<Quiz> listAnsCompare = new QuestionDAO().getQuesCompareQuiz(subId);
			int count = 0;
			for (Quiz quiz : listQuiz) {
				for (Quiz quiz1 : listAnsCompare) {
					if (quiz.getQuesId() == quiz1.getQuesId()) {
						if (quiz.getAnsId() == quiz1.getAnsId()) {
							count += 1;
						}
					}
				}
			}
			String numQues = new SubjectDAO().getNumQues(subId);
			int numQue = Integer.parseInt(numQues);
			float total = (float) 10 / numQue * count;
			float to = (float)((double) Math.round(total * 10) / 10);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("USER");
			String email = user.getEmail();
			new QuizDAO().updateTotalQuiz(to, email, quizId, quizTakeId);
			String timeStart = new QuizDAO().getTimeStart(email, quizId, quizTakeId);
			session.setAttribute("HOUR", null);
			session.setAttribute("MINUTES", null);
			session.setAttribute("SECONDS", null);
			session.setAttribute("QUIZTAKEID", null);
			session.setAttribute("SUBID", null);
			session.setAttribute("QUIZID", null);
			session.setAttribute("HAS", null);
			session.setAttribute("INDEX", null);
			session.setAttribute("LISTQUESS", null);
			request.setAttribute("TOTAL", to);
			request.setAttribute("RIGHTANS", count);
			request.setAttribute("TOTALQUES", numQue);
			request.setAttribute("DATESTART", timeStart);
			request.setAttribute("SUBID", subId);
			session.setAttribute("SUCCESS", null);
			url = SUCCESS;
		} catch (Exception e) {
			log("Finish_Quiz_Controller: " + e.toString());
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
