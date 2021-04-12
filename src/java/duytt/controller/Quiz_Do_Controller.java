/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.controller;

import duytt.daos.QuestionDAO;
import duytt.daos.QuizDAO;
import duytt.daos.SubjectDAO;
import duytt.dtos.Answer;
import duytt.dtos.Question;
import duytt.dtos.Quiz;
import duytt.dtos.Subject;
import duytt.dtos.User;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thant
 */
public class Quiz_Do_Controller extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private boolean checkSubIdInList(String subId) throws ClassNotFoundException, SQLException {
		List<Subject> listSub = new SubjectDAO().getSubject();
		for (Subject subject : listSub) {
			if (subject.getSubId().equals(subId)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkNumInList(String num, List<String> list) {

		for (String st : list) {
			if (st.equals(num)) {
				return false;
			}
		}
		return true;
	}
	private final static String TEST = "testQuiz.jsp";
	private final static String ERROR = "home.jsp";

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String url=ERROR;
		try {
			
			HttpSession session = request.getSession();
			session.setAttribute("SUCCESS", null);
			session.setAttribute("LSITQUIZ", null);
			List<Subject> listSub = new SubjectDAO().getSubject();
			String subId = request.getParameter("subId");
			boolean check = true;
			if (subId.isEmpty() || subId.equals("")) {
				check = false;
				request.setAttribute("SUBID_ERROR", "Please choose Quiz again");
			} else if (!checkSubIdInList(subId)) {
				check = false;
				request.setAttribute("SUBID_ERROR", "Please choose Quiz again");
			}

			if (check) {
				HashMap<Integer, List<Answer>> hasShow = null;
				User user = (User) session.getAttribute("USER");
				String email = user.getEmail();
				String quizTakeAndId = new QuizDAO().getQuizNotFinish(email, subId);
				int quizTmp = 0;
				int quizTD = 0;
				if (quizTakeAndId != null) {
					String[] quiz = quizTakeAndId.split(";");
					quizTmp = Integer.parseInt(quiz[1]);
					quizTD = Integer.parseInt(quiz[0]);
				}
				int quizTakeId = 0;
				int quizId = 0;
				if (quizTmp == 0) {
					Subject subTmp = null;
					for (Subject sub : listSub) {
						if (sub.getSubId().equals(subId)) {
							subTmp = new Subject();
							subTmp = sub;
							break;
						}
					}
					List<String> listNumQues = new ArrayList<>();
					int lastQ = new QuestionDAO().countQues(subId);
					while (listNumQues.size() < subTmp.getNumQues()) {
						int value = new Random().nextInt(lastQ) + 1;
						if (listNumQues.isEmpty()) {
							listNumQues.add(String.valueOf(value));
						} else {
							if (checkNumInList(String.valueOf(value), listNumQues)) {
								listNumQues.add(String.valueOf(value));
							}
						}
					}
					HashMap<Integer, List<Answer>> has = new QuizDAO().getQuesForTest(subId, listNumQues);
					quizId = new QuizDAO().createQuiz(subId);
					for (Integer te : has.keySet()) {
						new QuizDAO().createQuizDetail(quizId, te);
					}
					quizTakeId = new QuizDAO().createQuizTake(quizId, email);
					hasShow = new QuizDAO().getQuesForShowTest(quizId);
				} else {
					hasShow = new QuizDAO().getQuesForShowTest(quizTmp);
					quizTakeId = quizTD;
					quizId = quizTmp;
					List<Quiz> listQuiz = new QuizDAO().getQuizAnsTmp(quizTakeId, quizId);
					if (listQuiz != null) {
						session.setAttribute("LSITQUIZ", listQuiz);
					}
				}
				String timeQuiz = new SubjectDAO().getTime(subId);
				String timeStart = new QuizDAO().getTimeStart(email, quizId, quizTakeId);
				SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date parsedDate = d.parse(timeStart);
				Calendar cal = Calendar.getInstance();
				cal.setTime(parsedDate);
				cal.add(Calendar.MINUTE, Integer.parseInt(timeQuiz));
				String newTime = d.format(cal.getTime());
				Date ne = d.parse(newTime);
				Date now = new Date();
				long diff = ne.getTime() - now.getTime();
				long diffSeconds = (diff / 1000 % 60) + 1;
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000);
				session.setAttribute("HOUR", diffHours);
				session.setAttribute("MINUTES", diffMinutes);
				session.setAttribute("SECONDS", diffSeconds);

				session.setAttribute("QUIZTAKEID", quizTakeId);
				session.setAttribute("SUBID", subId);
				session.setAttribute("QUIZID", quizId);
				session.setAttribute("HAS", hasShow);
				String indexQu = (String) session.getAttribute("INDEX");
				session.setAttribute("INDEX", indexQu);
				List<Question> listQues = new QuestionDAO().getQuestion();
				session.setAttribute("LISTQUESS", listQues);
				url=TEST;
			}
		} catch (Exception e) {
			log("Show_Sub_Home_Controller: " + e.toString());
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
