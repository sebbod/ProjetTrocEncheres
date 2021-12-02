package fr.eni.bids.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import fr.eni.bids.BidsException;
import fr.eni.bids.bll.UserManager;
import fr.eni.bids.bll.utils.TrippleDes;
import fr.eni.bids.bo.User;

@Path("/user")
public class UserRST {
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	@SuppressWarnings("serial")
	@POST
	@Path("/signup")
	public Object create(Map<String, String> data) {
		try {
			TrippleDes crypto;
			String hPwd = "!bad_pwd!";
			try {
				crypto = new TrippleDes();
				hPwd = crypto.encrypt(data.get("pwd"));
			} catch (Exception e) {
				e.printStackTrace();
				return new HashMap<String, String>() {
					{
						put("message", e.getMessage());
					}
				};
			}
			User newUser = new User(data.get("pseudo"), data.get("name"), data.get("firstName"), data.get("email"), data.get("telephone"), data.get("street"), data.get("zipCode"), data.get("town"), hPwd);
			User u = new UserManager().add(newUser);
			u.setPwd(""); // don't send pwd to IHM
			return u;
		} catch (BidsException e) {
			e.printStackTrace();
			return new HashMap<String, String>() {
				{
					put("message", e.getMessage());
				}
			};
		}
	}

	@SuppressWarnings("serial")
	@PUT
	@Path("/modify")
	public Object update(Map<String, String> data) {
		try {
			System.out.println("generateObject, data=" + data);
			String pseudo = (String) data.get("pseudo");
			String pwd = (String) data.remove("pwd");
			User user = new UserManager().getByPseudoAndPassword(pseudo, pwd);
			for (Map.Entry<String, String> attribute : data.entrySet()) {
				String method = "set" + attribute.getKey().substring(0, 1).toUpperCase() + attribute.getKey().substring(1);
				User.class.getMethod(method, String.class).invoke(user, attribute.getValue());
			}
			User u = new UserManager().update(user);
			u.setPwd(""); // don't send pwd to IHM
			return u;
		} catch (BidsException | InvocationTargetException | NoSuchMethodException | IllegalAccessException exception) {
			exception.printStackTrace();
			return new HashMap<String, String>() {
				{
					put("message", exception.getMessage());
				}
			};
		}
	}

	@SuppressWarnings("serial")
	@DELETE
	@Path("/delete/{id: \\d+}")
	public Object delete(@PathParam("id") int id) {
		try {
			new UserManager().delete(id);
			return id;
		} catch (BidsException e) {
			e.printStackTrace();
			return new HashMap<String, String>() {
				{
					put("message", e.getMessage());
				}
			};
		}
	}

	@SuppressWarnings("serial")
	@GET
	@Path("/{id: \\d+}")
	public Object selectById(@PathParam("id") int id) {
		try {
			User u = new UserManager().getById(id);
			u.setPwd(""); // don't send pwd to IHM
			return u;
		} catch (BidsException e) {
			e.printStackTrace();
			return new HashMap<String, String>() {
				{
					put("message", e.getMessage());
				}
			};
		}
	}

	@SuppressWarnings("serial")
	@GET
	@Path("/{pseudo}")
	public Object selectByPseudo(@PathParam("pseudo") String pseudo) {
		try {
			User u = new UserManager().getByPseudo(pseudo);
			u.setPwd(""); // don't send pwd to IHM
			return u;
		} catch (BidsException e) {
			e.printStackTrace();
			return new HashMap<String, String>() {
				{
					put("message", e.getMessage());
				}
			};
		}
	}

	@SuppressWarnings("serial")
	@GET
	public Object selectAll() {
		try {
			List<User> uLst = new UserManager().getAll();
			uLst.forEach(u -> u.setPwd(""));// don't send pwd to IHM
			return uLst;
		} catch (BidsException e) {
			e.printStackTrace();
			return new HashMap<String, String>() {
				{
					put("message", e.getMessage());
				}
			};
		}
	}

}
