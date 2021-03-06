package fr.eni.bids.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bids.bll.BLLException;
import fr.eni.bids.bll.LoginManager;
import fr.eni.bids.bll.utils.AppUtils;
import fr.eni.bids.bll.utils.TrippleDes;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Show login page
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();

		// If remember me cookie is find, complete username
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					request.setAttribute("username", cookie.getValue());
				}
			}
		}
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp");
		rd.forward(request, response);
	}

	/**
	 * Try to login
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Link to data base
		LoginManager lMngr;
		int id = 0;

		try {
			lMngr = new LoginManager();
			TrippleDes crypto;
			String hPwd = "!bad_pwd!";
			try {
				crypto = new TrippleDes();
				hPwd = crypto.encrypt(password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			id = lMngr.validate(username, hPwd);
		} catch (BLLException e) {
			request.setAttribute("lstErrorCode", e.getLstErrorCode());
		}

		// If valid username/password, create session and redirect to homepage
		if (id != 0) {
			
			// If "remember me" is checked, create cookie with username
			if (request.getParameter("remember") != null) {
				if (request.getParameter("remember").equals("checked")) {
					Cookie cookie = new Cookie("username", username);
					// Expiration in 1 month
					cookie.setMaxAge(2628000);
					response.addCookie(cookie);
				}
			}

			HttpSession session = request.getSession();
			session.setAttribute("connectedUserId", id);

			// After 5 minutes of inactivity, invalidate that session
			session.setMaxInactiveInterval(5 * 60);

			// Check for redirect id
			int redirectId = -1;
			try {
				redirectId = Integer.parseInt(request.getParameter("redirectId"));
			} catch (Exception e) {
				// Ignore
			}
			
			String requestUri = AppUtils.getRedirectAfterLoginUrl(session, redirectId);
			
			// If hasn't valid URI to redirect, redirect to homepage
			if (requestUri == null) {
				response.sendRedirect(request.getContextPath() + "/");
			} else {
				response.sendRedirect(requestUri);
			}
			
		// Else, redirect to login page with error
		} else {
			request.setAttribute("error", "Identifiant ou mot de passe invalide");
			request.setAttribute("username", username);
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp");
			rd.forward(request, response);
		}

	}

}
