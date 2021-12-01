package fr.eni.bids.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import fr.eni.bids.BidsException;
import fr.eni.bids.bll.CategoryManager;
import fr.eni.bids.bll.ItemManager;
import fr.eni.bids.bo.Category;

@Path("/category")
public class CategoryRST {
	@Context
	private HttpServletRequest request;

	@SuppressWarnings("serial")
	@GET
	@Path("/{id: \\d+}")
	public Object selectById(@PathParam("id") int id) {
		try {
			return new ItemManager().getById(id);
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
			return new CategoryManager().getAll();
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
	@POST
	@Path("/new")
	public Object create(Map<String, Object> data) {
		return new Category();
		//TODO
		/*
		try {			
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			int connectedUserId = (int) request.getSession().getAttribute("connectedUserId");
			User seller = new UserManager().getById(connectedUserId);
			Category category = data.get("category") == null || ((String) data.get("category")).isEmpty() ? null : new CategoryManager().getById((int) data.get("category"));
			Item newItem = new Item((String) data.get("name"), (String) data.get("description"), LocalDateTime.parse((String) data.get("dateStart"), formatter), LocalDateTime.parse((String) data.get("dateEnd"), formatter),
					(int) data.get("priceSeller"), seller, category);
			Item item = new ItemManager().add(newItem);
			// The instance of pickUpAdr is automatically added. It is updated if the address data have been modified.
			updatePickUpAdress(seller, item, data);
			return item;			
			
		} catch (BidsException e) {
			e.printStackTrace();
			return new HashMap<String, String>() {
				{
					put("message", e.getMessage());
				}
			};
		}
		*/
	}

}
