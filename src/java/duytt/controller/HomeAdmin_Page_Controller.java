/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.controller;

import duytt.daos.QuestionDAO;
import duytt.daos.SubjectDAO;
import duytt.dtos.Answer;
import duytt.dtos.Question;
import duytt.dtos.Subject;
import java.io.IOException;
import java.util.HashMap;
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
public class HomeAdmin_Page_Controller extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private final static String HOMEADIM = "homeAdmin.jsp";

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			HttpSession session = request.getSession();

			boolean check = true;
			String index = request.getParameter("index");
			if (index == null) {
				index = "1";
			} else if (!index.matches("[0-9]+") || index.isEmpty() || index.equals("")) {
				check = false;
				request.setAttribute("INDEX_ERROR", "Please Search Again");
			}

			List<Subject> listSub = new SubjectDAO().getSubject();
			String subId = request.getParameter("sub");
			if (subId == null) {
				subId = listSub.get(0).getSubId();
			}
			if (subId.isEmpty() || subId.equals("")) {
				check = false;
				request.setAttribute("SUBID_ERROR", "Please Choose again SUBJECT");

			}
			if (!subId.equals(listSub.get(0).getSubId())) {
				session.setAttribute("SUCCESS", null);
			}
			if(!index.equals("1")){
				session.setAttribute("SUCCESS", null);
			}
			if (check) {

				HashMap<Integer, List<Answer>> has = new QuestionDAO().getData(Integer.parseInt(index), subId);
				List<Question> listQues = new QuestionDAO().getQuestion();
				int soluong = new QuestionDAO().countQues(subId);
				int sopage = soluong / 20;
				if (soluong % 20 != 0) {
					sopage++;
				}

				request.setAttribute("HASMAP", has);
				request.setAttribute("LISTQUES", listQues);

				request.setAttribute("INDEX", index);
				session.setAttribute("SOPAGE", sopage);
				session.setAttribute("SUBID", subId);
				session.setAttribute("TXTSEARCH", null);
				session.setAttribute("STATUS", null);
				session.setAttribute("CONTROLLER", "HomeAdmin_Page_Controller");
			}

			session.setAttribute("LISTSUB", listSub);
		} catch (Exception e) {
			log("HomeAdmin_Page_Controller: " + e.toString());
		} finally {
			request.getRequestDispatcher(HOMEADIM).forward(request, response);
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
