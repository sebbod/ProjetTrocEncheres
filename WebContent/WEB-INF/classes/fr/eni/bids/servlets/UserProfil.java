package fr.eni.bids.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bids.bll.BLLException;
import fr.eni.bids.bll.UserManager;
import fr.eni.bids.bo.User;
import fr.eni.bids.rest.ErrorCodes;

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

		HttpSession session = request.getSession();
		User u = new User();
		User cu = (User) session.getAttribute("connectedUser");
		String action = request.getParameter("action");
		if (action == null) {
			action = "select";
		}
		// Debug message
		System.out.println("Servlet UserProfil - doGet(), action=" + action);
		System.out.println("Servlet UserProfil - doGet(), cu=" + cu);

		if (action == "insert") {
			session.setAttribute("action", "insert");
		} else {
			// for select and update
			String strId = request.getParameter("userId");
			int id = 1;
			if (strId != null) {
				id = Integer.parseInt(strId);
			}
			if (action.equals("update") || action.equals("save") || action.equals("delete")) {
				// check user can update this profil
				System.out.println("Servlet UserProfil - doGet(), cu.getId()=" + cu.getId());
				System.out.println("Servlet UserProfil - doGet(), Id=" + id);
				if (!cu.getId().equals(id)) {
					// otherwise throw exception
					BLLException be = new BLLException();
					be.add(ErrorCodes.USER_UPDATE_FORBIDDEN);
					request.setAttribute("lstErrorCode", be.getLstErrorCode());
					session.setAttribute("action", "select");
					RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/User/Profile.jsp");
					rd.forward(request, response);
					return;
				}
				session.setAttribute("action", "update");
			} else {
				session.setAttribute("action", "select");
			}

			// User - link to data base
			UserManager uMngr;

			try {
				uMngr = new UserManager();
				u = uMngr.getById(id);
			} catch (BLLException e) {
				request.setAttribute("lstErrorCode", e.getLstErrorCode());
			}

			// Save user for the session
			session.setAttribute("connectedUser", u);
			// ^^ !!! temporaire !!! code ^^ a supprimer quand la page de cnx sera faite
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
