package fr.eni.bids.rest;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import fr.eni.bids.BidsException;
import fr.eni.bids.bll.CategoryManager;
import fr.eni.bids.bll.ItemManager;

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

}
