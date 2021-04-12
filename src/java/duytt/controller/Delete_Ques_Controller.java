/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.controller;

import duytt.daos.QuestionDAO;
import duytt.dtos.Question;
import java.io.IOException;
import java.sql.SQLException;
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
public class Delete_Ques_Controller extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private boolean checkQuesIdInList(int quesId) throws ClassNotFoundException, SQLException {
		List<Question> listQues = new QuestionDAO().getQuestion();
		for (Question listQue : listQues) {
			if (listQue.getQuesId() == quesId) {
				return true;
			}
		}
		return false;
	}
	private final static String HOMEADIM = "HomeAdmin_Page_Controller";
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String url=HOMEADIM;
		try {
			HttpSession session = request.getSession();
			session.setAttribute("SUCCESS", null);
			String txtQuesId = request.getParameter("txtQuesId");
			url=request.getParameter("controller");
			boolean check = true;
			if (!txtQuesId.matches("[0-9]+")) {
				check = false;
				request.setAttribute("QUESID_ERROR", "Please Check Again");
			} else if (!checkQuesIdInList(Integer.parseInt(txtQuesId))) {
				check = false;
				request.setAttribute("QUESID_ERROR", "Please Check Again");
			}
			
			if(check)
			{
				new QuestionDAO().updateQuestionStatus(Integer.parseInt(txtQuesId), "false");
			}
		} catch (Exception e) {
			log("Delete_Ques_Controller:" + e.toString());
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
