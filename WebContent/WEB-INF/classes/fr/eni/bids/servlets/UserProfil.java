package fr.eni.bids.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bids.bll.BLLException;
import fr.eni.bids.bll.UserManager;
import fr.eni.bids.bo.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/user/profil")
public class UserProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfil() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String strId = request.getParameter("userId");

		int id = 1;
		if (strId != null) {
			id = Integer.getInteger(strId);
		}

		// Debug message
		System.out.println("Servlet UserProfil - doGet(), id=" + id);

		// User - link to data base
		UserManager uMngr;
		User u = null;

		try {
			uMngr = new UserManager();
			u = uMngr.getById(id);
		} catch (BLLException e) {
			List<Integer> lstErrorCode = (List<Integer>) request.getAttribute("lstErrorCode");

			if (lstErrorCode == null) {
				lstErrorCode = new ArrayList<Integer>();
				request.setAttribute("lstErrorCode", lstErrorCode);
			}

			lstErrorCode.addAll(e.getLstErrorCode());
		}

		request.getServletContext().setAttribute("user", u);

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/User/Profile.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
