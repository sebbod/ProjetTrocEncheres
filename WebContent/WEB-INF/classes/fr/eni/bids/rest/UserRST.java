package fr.eni.bids.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import fr.eni.bids.BidsException;
import fr.eni.bids.bll.UserManager;
import fr.eni.bids.bo.User;
import fr.eni.bids.msg.ErrorCodes;

@Path("/user")
public class UserRST {
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	@POST
	@Path("/signup")
	public Object create(Map<String, String> data) {
		try {
			User newUser = new User(data.get("pseudo"), data.get("name"), data.get("firstName"), data.get("email"),
					data.get("telephone"), data.get("street"), data.get("zipCode"), data.get("town"), data.get("pwd"));
			User user = new UserManager().add(newUser);
			generateNewSession(user);
			return newUser;
		} catch (BidsException eException) {
			eException.printStackTrace();
			return new HashMap<String, String>() {
				{
					put("message", eException.getMessage());
				}
			};
		}
	}

	@PUT
	@Path("/modify")
	public Object update(Map<String, String> data) {
		try {
			String pseudo = (String) data.get("pseudo");
			String motDePasse = (String) data.remove("motDePasseActuel");
			User user = new UserManager().getByPseudoAndPassword(pseudo, motDePasse);
			for (Map.Entry<String, String> attribute : data.entrySet()) {
				String method = "set" + attribute.getKey().substring(0, 1).toUpperCase()
						+ attribute.getKey().substring(1);
				User.class.getMethod(method, String.class).invoke(user, attribute.getValue());
			}
			return new UserManager().update(user);
		} catch (BidsException | InvocationTargetException | NoSuchMethodException | IllegalAccessException exception) {
			exception.printStackTrace();
			return new HashMap<String, String>() {
				{
					put("message", exception.getMessage());
				}
			};
		}
	}

	@DELETE
	@Path("/delete/{id: \\d+}")
	public void delete(@PathParam("id") int id) {
		try {
			new UserManager().delete(id);
		} catch (BidsException eException) {
			eException.printStackTrace();
		}
	}

	@GET
	@Path("/signin")
	public Object authenticate(@QueryParam("pseudo") String pseudo, @QueryParam("pwd") String pwd,
			@QueryParam("rememberMe") boolean rememberMe) {
		try {
			User user = new UserManager().getByPseudoAndPassword(pseudo, pwd);
			if (user != null) {
				generateNewSession(user, rememberMe);
			}
			return user;
		} catch (BidsException eException) {
			eException.printStackTrace();
			return new HashMap<String, String>() {
				{
					put("message", eException.getMessage());
				}
			};
		}
	}

	@GET
	@Path("/signout")
	public Object logout() {
		for (int index = 0; index < request.getCookies().length; index++) {
			Cookie cookie = request.getCookies()[index];
			if (cookie.getName().equals("connectedUserId")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie); // Send back an expired cookie.
			}
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return false;
	}

	@GET
	@Path("/{id: \\d+}")
	public Object selectById(@PathParam("id") int id) {
		try {
			return new UserManager().getById(id);
		} catch (BidsException eException) {
			eException.printStackTrace();
			return new HashMap<String, String>() {
				{
					put("message", eException.getMessage());
				}
			};
		}
	}

	@GET
	@Path("/{pseudo}")
	public Object selectByPseudo(@PathParam("pseudo") String pseudo) {
		try {
			return new UserManager().getByPseudo(pseudo);
		} catch (BidsException eException) {
			eException.printStackTrace();
			return new HashMap<String, String>() {
				{
					put("message", eException.getMessage());
				}
			};
		}
	}

	@GET
	public Object selectAll() {
		try {
			return new UserManager().getAll();
		} catch (BidsException eException) {
			eException.printStackTrace();
			return new HashMap<String, String>() {
				{
					put("message", eException.getMessage());
				}
			};
		}
	}

	@GET
	@Path("/session")
	public Object checkValidity(@CookieParam("connectedUserId") String connectedUserId) {
		try {
			if (connectedUserId != null) {
				UserManager userManager = new UserManager();
				User user = userManager.getById(Integer.parseInt(connectedUserId));
				generateNewSession(user);
				return user;
			}
			HttpSession session = request.getSession(false);
			if (session == null) {
				return false;
			}
			return session.getAttribute("User");
		} catch (BidsException eException) {
			try {
				throw new BidsException(ErrorCodes.SESSION_VALIDATION_ERROR, eException);
			} catch (BidsException eExceptionValidation) {
				eExceptionValidation.printStackTrace();
				return new HashMap<String, String>() {
					{
						put("message", eExceptionValidation.getMessage());
					}
				};
			}
		}
	}

	public void generateNewSession(User user, boolean rememberMe) {
		HttpSession session = request.getSession(true);
		session.setAttribute("User", user);
		if (rememberMe) {
			String connectedUserId = String.valueOf(user.getId());
			Cookie cookie = new Cookie("connectedUserId", connectedUserId);
			cookie.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
		}
	}

	public void generateNewSession(User user) {
		generateNewSession(user, false);
	}
}
