package fr.eni.bids.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bids.BidsException;
import fr.eni.bids.bll.CategoryManager;
import fr.eni.bids.bll.utils.AppUtils;
import fr.eni.bids.bo.Category;
import fr.eni.bids.bo.User;

/**
 * Servlet implementation class Sell
 */
@WebServlet("/sell")
public class Sell extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sell() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Show sell page
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get all categories
		List<Category> categories = null;
		try {
			categories = new CategoryManager().getAll();
		} catch (BidsException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		request.setAttribute("categories", categories);
		
		// Get user address
		String 	userStreet = null,
				userZip = null,
				userTown = null;
		User user;
		user = AppUtils.getConnectedUser(request.getSession());
		userStreet = user.getStreet();
		userZip = user.getZipCode();
		userTown = user.getTown();
		
		request.setAttribute("userStreet", userStreet);
		request.setAttribute("userZip", userZip);
		request.setAttribute("userTown", userTown);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Sell.jsp");
		rd.forward(request, response);
	}
}
