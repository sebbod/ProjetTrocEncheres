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
import fr.eni.bids.bll.BidManager;
import fr.eni.bids.bll.ItemManager;
import fr.eni.bids.bll.UserManager;
import fr.eni.bids.bo.Bid;
import fr.eni.bids.bo.Item;
import fr.eni.bids.bo.User;

@Path("/bid")
public class BidRST {
	@Context
	private HttpServletRequest request;

	@SuppressWarnings("serial")
	@GET
	@Path("/highest/{itemId: \\d+}")
	public Object getHighestBid(@PathParam("itemId") int itemId) {
		try {
			Bid bid = new BidManager().getHighestBid(itemId);
			return bid != null ? bid : false;
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
		try {
			Item item = new ItemManager().getById((int) data.get("itemId"));
			int connectedUserId = (int) request.getSession().getAttribute("connectedUserId");
			User buyer = new UserManager().getById(connectedUserId);
			Bid bid = new Bid(buyer, item, (int) data.get("amout"));
			return new BidManager().add(bid);
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