package fr.eni.bids.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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
			User user = new UserManager().add(newUser);
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
			System.out.println("generateObject, data=" + data);
			String pseudo = (String) data.get("pseudo");
			String pwd = (String) data.remove("pwd");
			User user = new UserManager().getByPseudoAndPassword(pseudo, pwd);
			for (Map.Entry<String, String> attribute : data.entrySet()) {
				String method = "set" + attribute.getKey().substring(0, 1).toUpperCase() + attribute.getKey().substring(1);
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

}
