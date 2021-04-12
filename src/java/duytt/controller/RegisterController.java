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
public class RegisterController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private final static String ERROR = "register.jsp";
	private final static String SUCCESS = "Show_Sub_Home_Controller";
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
		response.setContentType("text/html;charset=UTF-8");
		String url = ERROR;
		try {
			HttpSession session = request.getSession();
			session.setAttribute("SUCCESS", null);
			String userId = request.getParameter("userId");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");
			boolean check = true;
			if (userId.isEmpty()) {
				check = false;
				session.setAttribute("userId_ERROR_re", "Your Account not empty");
			} else if (userId.length() > 100) {
				check = false;
				session.setAttribute("userId_ERROR_re", "Your Account length <= 50");
			} else if (!userId.matches("[A-Za-z0-9]+@gmail.com") && !userId.matches("[A-Za-z0-9]+@fpt.edu.vn")) {
				check = false;
				session.setAttribute("userId_ERROR_re", "Email must have @gmail.com or @fpt.edu.vn");
			} else if (!new UserDAO().checkUser(userId)) {
				check = false;
				session.setAttribute("userId_ERROR_re", "Email has been sign up");
			} else {
				session.setAttribute("userAccount_re", userId);
			}
			if (password.isEmpty()) {
				check = false;
				session.setAttribute("password_ERROR_re", "Password not empty");
			}
			if (name.isEmpty()) {
				check = false;
				session.setAttribute("name_ERROR_re", "name not empty");
			} else if (name.chars().anyMatch(Character::isDigit)) {
				check = false;
				session.setAttribute("name_ERROR_re", "name only have number");
			}

			if (repassword.isEmpty()) {
				check = false;
				session.setAttribute("repassword_ERROR_re", "rePassword not empty");
			} else if (!repassword.equals(password)) {
				check = false;
				session.setAttribute("repassword_ERROR_re", "Repassword isn't like password");
			}
			
			if(check)
			{
				User user = new User(userId, toHexString(getSHA(password)), name, "SU", true);
				new UserDAO().addUser(user);
				user.setPassword("");
				session.setAttribute("USER", user);
				url=SUCCESS;
			}	
		} catch (Exception e) {
			log("RegisterController: "+e.toString());
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
