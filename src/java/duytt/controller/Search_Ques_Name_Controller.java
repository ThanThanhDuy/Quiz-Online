/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.controller;

import duytt.daos.QuestionDAO;
import duytt.daos.SubjectDAO;
import duytt.dtos.QuesSearch;
import duytt.dtos.Question;
import duytt.dtos.Subject;
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
public class Search_Ques_Name_Controller extends HttpServlet {

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
			session.setAttribute("SUCCESS", null);
			boolean check = true;
			String index = request.getParameter("index");
			if (index == null) {
				index = "1";
			} else if (!index.matches("[0-9]+") || index.isEmpty() || index.equals("")) {
				check = false;
				request.setAttribute("INDEX_ERROR", "Please Search Again");
			}
			List<Subject> listSub = new SubjectDAO().getSubject();
			String txtSearch = request.getParameter("txtSearch");
			if (check) {
				List<QuesSearch> list=new QuestionDAO().getDataSearch(Integer.parseInt(index), txtSearch);				
				List<Question> listQues = new QuestionDAO().getQuestion();
				int soluong = new QuestionDAO().countQuesSearch(txtSearch);
				int sopage = soluong / 2;
				if (soluong % 2 != 0) {
					sopage++;
				}
				request.setAttribute("LISTQUES", listQues);
				request.setAttribute("LISTS", list);
				request.setAttribute("INDEX", index);
				session.setAttribute("TXTSEARCH", txtSearch);
				session.setAttribute("SOPAGE", sopage);
				session.setAttribute("SUBID", null);
				session.setAttribute("STATUS", null);
				session.setAttribute("CONTROLLER", "Search_Ques_Name_Controller");
			}
			session.setAttribute("LISTSUB", listSub);

		} catch (Exception e) {
			log("Search_Ques_Name_Controller: " + e.toString());
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
