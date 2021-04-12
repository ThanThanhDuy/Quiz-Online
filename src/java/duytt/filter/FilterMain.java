/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.filter;

import duytt.dtos.User;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thant
 */
@WebFilter(filterName = "Filter", urlPatterns = {"/*"})
public class FilterMain implements Filter {

	private final List<String> USER;
	private final List<String> ADMIN;
	private final String SU = "SU";
	private final String AD = "AD";
	private final String LOGIN = "login.jsp";

	private static final boolean debug = true;

	// The filter configuration object we are associated with.  If
	// this value is null, this filter instance is not currently
	// configured. 
	private FilterConfig filterConfig = null;

	public FilterMain() {
		USER = new ArrayList<>();
		USER.add("");
		USER.add("login.jsp");
		USER.add("LoginController");
		USER.add("MainController");
		USER.add("LogoutController");
		USER.add("home.jsp");
		USER.add("more.jsp");
		USER.add("showMark.jsp");
		USER.add("history.jsp");
		USER.add("Show_Sub_Home_Controller");
		USER.add("Quiz_Do_Controller");
		USER.add("Quiz_Do_ans_Controller");
		USER.add("Finish_Quiz_Controller");
		USER.add("View_History_Controller");
		USER.add("More_Controller");

		ADMIN = new ArrayList<>();
		ADMIN.add("");
		ADMIN.add("login.jsp");
		ADMIN.add("LoginController");
		ADMIN.add("MainController");
		ADMIN.add("LogoutController");
		ADMIN.add("homeAdmin.jsp");
		ADMIN.add("create_qes.jsp");
		ADMIN.add("CreateQues_Page_Controller");
		ADMIN.add("HomeAdmin_Page_Controller");
		ADMIN.add("Create_Ques_Controller");
		ADMIN.add("Update_Question_Controller");
		ADMIN.add("Search_Ques_Name_Controller");
		ADMIN.add("Status_Ques_Controller");
		ADMIN.add("Delete_Ques_Controller");
		ADMIN.add("More_Admin_Controller");
		

	}

	private void doBeforeProcessing(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		if (debug) {
			log("Filter:DoBeforeProcessing");
		}

		// Write code here to process the request and/or response before
		// the rest of the filter chain is invoked.
		// For example, a logging filter might log items on the request object,
		// such as the parameters.
		/*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
		 */
	}

	private void doAfterProcessing(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		if (debug) {
			log("Filter:DoAfterProcessing");
		}

		// Write code here to process the request and/or response after
		// the rest of the filter chain is invoked.
		// For example, a logging filter might log the attributes on the
		// request object after the request has been processed. 
		/*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
		 */
		// For example, a filter might append something to the response.
		/*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
		 */
	}

	/**
	 *
	 * @param request The servlet request we are processing
	 * @param response The servlet response we are creating
	 * @param chain The filter chain we are processing
	 *
	 * @exception IOException if an input/output error occurs
	 * @exception ServletException if a servlet error occurs
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			String uri = req.getRequestURI();
			if (uri.contains(".png") || uri.contains(".jpg") || uri.contains("gif")) {
				chain.doFilter(request, response);
			} else {
				if (uri.contains("register.jsp") || uri.contains("login.jsp") || uri.contains("LoginController")
						|| uri.contains("RegisterController") || uri.contains("MainController") || uri.contains("Register_Page_Controller") ||
						uri.contains("Login_Page_Controller")) {
					chain.doFilter(request, response);
					return;
				}
				int index = uri.lastIndexOf("/");
				String resout = uri.substring(index + 1);
				HttpSession session = req.getSession();
				if (session == null || session.getAttribute("USER") == null) {
					res.sendRedirect(LOGIN);
				} else {
					User user = (User) session.getAttribute("USER");
					String roleID = user.getRoleId();
					if (AD.equals(roleID) && ADMIN.contains(resout)) {
						chain.doFilter(request, response);
					} else if (SU.equals(roleID) && USER.contains(resout)) {
						chain.doFilter(request, response);
					} else {
						res.sendRedirect(LOGIN);
					}
				}
			}
		} catch (Exception e) {
			log("FilterMain: " + e.toString());
		}

	}

	/**
	 * Return the filter configuration object for this filter.
	 */
	public FilterConfig getFilterConfig() {
		return (this.filterConfig);
	}

	/**
	 * Set the filter configuration object for this filter.
	 *
	 * @param filterConfig The filter configuration object
	 */
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * Destroy method for this filter
	 */
	public void destroy() {
	}

	/**
	 * Init method for this filter
	 */
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
		if (filterConfig != null) {
			if (debug) {
				log("Filter:Initializing filter");
			}
		}
	}

	/**
	 * Return a String representation of this object.
	 */
	@Override
	public String toString() {
		if (filterConfig == null) {
			return ("Filter()");
		}
		StringBuffer sb = new StringBuffer("Filter(");
		sb.append(filterConfig);
		sb.append(")");
		return (sb.toString());
	}

	private void sendProcessingError(Throwable t, ServletResponse response) {
		String stackTrace = getStackTrace(t);

		if (stackTrace != null && !stackTrace.equals("")) {
			try {
				response.setContentType("text/html");
				PrintStream ps = new PrintStream(response.getOutputStream());
				PrintWriter pw = new PrintWriter(ps);
				pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

				// PENDING! Localize this for next official release
				pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
				pw.print(stackTrace);
				pw.print("</pre></body>\n</html>"); //NOI18N
				pw.close();
				ps.close();
				response.getOutputStream().close();
			} catch (Exception ex) {
			}
		} else {
			try {
				PrintStream ps = new PrintStream(response.getOutputStream());
				t.printStackTrace(ps);
				ps.close();
				response.getOutputStream().close();
			} catch (Exception ex) {
			}
		}
	}

	public static String getStackTrace(Throwable t) {
		String stackTrace = null;
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			pw.close();
			sw.close();
			stackTrace = sw.getBuffer().toString();
		} catch (Exception ex) {
		}
		return stackTrace;
	}

	public void log(String msg) {
		filterConfig.getServletContext().log(msg);
	}

}
