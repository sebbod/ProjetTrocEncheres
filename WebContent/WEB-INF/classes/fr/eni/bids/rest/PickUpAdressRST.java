package fr.eni.bids.rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import fr.eni.bids.BidsException;
import fr.eni.bids.bll.PickUpAdressManager;

@Path("/pickupadress")
public class PickUpAdressRST {

	@SuppressWarnings("serial")
	@GET
	@Path("/{itemId: \\d+}")
	public Object selectById(@PathParam("itemId") int itemId) {
		try {
			return new PickUpAdressManager().getById(itemId);
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
