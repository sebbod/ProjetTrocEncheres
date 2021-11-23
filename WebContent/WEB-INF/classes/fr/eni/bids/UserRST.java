package fr.eni.bids;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import fr.eni.bids.bll.BLLException;
import fr.eni.bids.bll.UserManager;
import fr.eni.bids.bo.User;

@Path("/User")
public class UserRST {

	@GET
	public List<User> getAll() {
		UserManager uMngr;
		List<User> uLst = null;
		try {
			uMngr = new UserManager();
			uLst = uMngr.getAll();
		} catch (BLLException e) {
			System.out.println("UserRST::getAll() - e.getMessage()=" + e.getMessage());
		}
		return uLst;
	}

	@GET
	@Path("/{id : \\d+}")
	public User getById(@PathParam("id") int id) {
		UserManager uMngr;
		User u = null;
		try {
			uMngr = new UserManager();
			u = uMngr.getById(id);
		} catch (BLLException e) {
			System.out.println("UserRST::getById(" + id + ") - e.getMessage()=" + e.getMessage());
		}
		return u;
	}

	@POST
	public User insert(@FormParam("pseudo") String pseudo, @FormParam("name") String name,
			@FormParam("firstName") String firstName, @FormParam("email") String email,
			@FormParam("telephone") String telephone, @FormParam("street") String street,
			@FormParam("zipCode") String zipCode, @FormParam("town") String town, @FormParam("pwd") String pwd) {
		UserManager uMngr;
		try {

			uMngr = new UserManager();
			uMngr.insert(pseudo, name, firstName, email, telephone, street, zipCode, town, pwd);
		} catch (BLLException e) {
			System.out.println("UserRST::insert(...) - e.getMessage()=" + e.getMessage());
		}

		User u = null;
		/*
		 * TODO
		 * 
		 * try { uMngr = new UserManager(); u = uMngr.getById(id); } catch (BLLException
		 * e) { System.out.println("UserRST::getById(" + id + ") - e.getMessage()=" +
		 * e.getMessage()); }
		 */
		return u;
	}

	@PUT
	@Path("/{id : \\d+}")
	public User update(@FormParam("id") int id, @FormParam("pseudo") String pseudo, @FormParam("name") String name,
			@FormParam("firstName") String firstName, @FormParam("email") String email,
			@FormParam("telephone") String telephone, @FormParam("street") String street,
			@FormParam("zipCode") String zipCode, @FormParam("town") String town, @FormParam("pwd") String pwd) {
		UserManager uMngr;
		User u = null;
		try {

			uMngr = new UserManager();
			uMngr.update(id, pseudo, name, firstName, email, telephone, street, zipCode, town, pwd);
		} catch (BLLException e) {
			System.out.println("UserRST::update(...) - e.getMessage()=" + e.getMessage());
		}
		return u;
	}

	@DELETE
	@Path("/{id : \\d+}")
	public boolean delete(@PathParam("id") int id) {
		UserManager uMngr;
		try {
			uMngr = new UserManager();
			uMngr.delete(id);
		} catch (BLLException e) {
			System.out.println("UserRST::delete(" + id + ") - e.getMessage()=" + e.getMessage());
		}
		return false;
	}
}
