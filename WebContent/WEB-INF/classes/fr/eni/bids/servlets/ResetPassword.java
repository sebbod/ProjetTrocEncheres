package fr.eni.bids.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bids.BidsException;
import fr.eni.bids.bll.UserManager;
import fr.eni.bids.bll.utils.MailUtils;
import fr.eni.bids.bll.utils.SecurityUtils;
import fr.eni.bids.bo.User;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet("/reset-password")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String host;
	private String port;
	private String email;
	private String name;
	private String pass;
	
	public void init() {
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		email = context.getInitParameter("email");
		name = context.getInitParameter("name");
		pass = context.getInitParameter("pass");
		System.out.println("host: " + host + ", port: " + port + ", email: " + email + ", name: " + name + ", pass: " + pass);
	}
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResetPassword() {
		super();
	}
	
	/**
	 * Show reset password page
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/ResetPassword.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * Try to reset password
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Find user by username
		String username = request.getParameter("username");
		User user = null;
		try {
			UserManager uMngr = new UserManager();
			user = uMngr.getByPseudo(username);
		} catch (BidsException e) {
			e.printStackTrace();
		}
		
		// Change password
		String newPassword = "";
		
		
		// Send email
		String recipient = user.getEmail();
		String subject = "Votre mot de passe a été réinitialisé";
		String content = "Votre mot de passe a bien été réinitialisé. Voici votre nouveau mot de passe : " + newPassword;
		content += "Pour des raisons de sécurité, vous devriez changer votre mot de passe après votre connexion";
		String message = "";
		try {
			MailUtils.sendEmail(host, port, email, name, pass, recipient, subject, content);
			message = "Si votre identifiant et adresse mail correspondent, votre mot de passe a été réinitialisé avec succès. Veuiller consulter votre adresse mail.";
		} catch (Exception e) {
			e.printStackTrace();
			message = "Une erreur est servenue, veuillez réessayer.";
		}
	}
}
