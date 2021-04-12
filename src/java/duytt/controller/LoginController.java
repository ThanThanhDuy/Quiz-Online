/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.controller;

import duytt.daos.UserDAO;
import duytt.dtos.User;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thant
 */
public class LoginController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private final static String ERROR = "login.jsp";
	private final static String SUCCESS = "Show_Sub_Home_Controller";
	private final static String ADMIN = "HomeAdmin_Page_Controller";
	private byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    
    private static String toHexString(byte[] hash) 
    { 
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);  
  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
        return hexString.toString();  
    }
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = ERROR;
		try {
			HttpSession session = request.getSession();
			boolean check = true;
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			session.setAttribute("userID_ERROR", null);
			session.setAttribute("SUCCESS", null);
			session.setAttribute("password_ERROR", null);
			session.setAttribute("userAccount", null);
			if (userId.isEmpty() || userId == null) {
				check = false;
				session.setAttribute("userId_ERROR", "Your Account not empty");
			} else if (userId.length() > 100) {
				check = false;
				session.setAttribute("userId_ERROR", "Your Account length <= 50");
			} else if (!userId.matches("[A-Za-z0-9]+@gmail.com") && !userId.matches("[A-Za-z0-9]+@fpt.edu.vn")) {
				check = false;
				session.setAttribute("userId_ERROR", "Email must have @gmail.com or @fpt.edu.vn");
			} else {
				session.setAttribute("userAccount", userId);
			}
			if (password.isEmpty() || password == null) {
				check = false;
				session.setAttribute("password_ERROR", "Password not empty");
			}

			if (check) {
				User user = new UserDAO().checkLogin(userId, toHexString(getSHA(password)));
				if (user != null) {
					if (user.isStatus()) {
						session.setAttribute("USER", user);
						session.setAttribute("Error_Login", null);
						session.setAttribute("SUCCESS", "success");
						if (user.getRoleId().equals("AD")) {
							session.setAttribute("userID_ERROR", null);
							session.setAttribute("password_ERROR", null);
							session.setAttribute("userAccount", null);
							
							
							url = ADMIN;
						} else if (user.getRoleId().equals("SU")) {
							session.setAttribute("userID_ERROR", null);
							session.setAttribute("password_ERROR", null);
							session.setAttribute("userAccount", null);
							
							url = SUCCESS;
						}
					} else {
						session.setAttribute("Error_Login", "The account has been locked");
					}
				} else {
					session.setAttribute("Error_Login", "Wrong User Account or Password. Please try again!");
				}
			}
		} catch (Exception e) {
			log("LoginController: "+e.toString());
		} finally {
			response.sendRedirect(url);
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
