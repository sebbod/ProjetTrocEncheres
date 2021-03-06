package fr.eni.bids.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import fr.eni.bids.BidsException;
import fr.eni.bids.bll.CategoryManager;
import fr.eni.bids.bll.ItemManager;
import fr.eni.bids.bll.PickUpAdressManager;
import fr.eni.bids.bll.UserManager;
import fr.eni.bids.bo.Category;
import fr.eni.bids.bo.Item;
import fr.eni.bids.bo.PickUpAdress;
import fr.eni.bids.bo.User;

@Path("/item")
public class ItemRST {
	@Context
	private HttpServletRequest request;
	private int connectedUserId = 0;

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
	@POST
	@Path("/new")
	public Object create(Map<String, Object> data) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm");
			int connectedUserId = (int) request.getSession().getAttribute("connectedUserId");
			User seller = new UserManager().getById(connectedUserId);
			//System.out.println(data.toString());
			String dataCategory = (String) data.get("category");
			Category category = (dataCategory == null || dataCategory.isEmpty() ? null : new CategoryManager().getById(Integer.parseInt(dataCategory)));
			Integer dataPriceSeller = Integer.parseInt((String) data.get("priceSeller"));
			//System.out.println(dataPriceSeller);
			Item newItem = new Item((String) data.get("name"), (String) data.get("description"), LocalDateTime.parse((String) data.get("dateStart"), formatter), LocalDateTime.parse((String) data.get("dateEnd"), formatter), dataPriceSeller,
					seller, category);
			System.out.println("Before add : " + newItem.toString());
			Item item = new ItemManager().add(newItem);
			System.out.println("After add : " + item.toString());
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
	}

	@SuppressWarnings("serial")
	@GET
	@Path("/search4buy")
	public Object itemSearch(@QueryParam("userSearch") String userSearch, @QueryParam("category") String category, @QueryParam("saleIsOpen") boolean saleIsOpen, @QueryParam("isCurrentUser") boolean isCurrentUser,
			@QueryParam("saleIsWon") boolean saleIsWon) {
		//System.out.println("search4buy userSearch=" + userSearch + ", category=" + category + ", saleIsOpen=" + saleIsOpen + ", isCurrentUser=" + isCurrentUser + ", saleIsWon=" + saleIsWon);
		try {
			ItemManager itemMngr = new ItemManager();

			if (category.isEmpty()) {
				category = null;
			}
			// first filter
			List<Item> items = itemMngr.getItemsLike(userSearch, category);
			//System.out.println("search4buy items=" + items.size());
			if (request.getSession(false) != null && request.getSession(false).getAttribute("connectedUserId") != null) {
				// connected mode
				this.connectedUserId = (int) request.getSession().getAttribute("connectedUserId");
				items = items.stream().filter(item -> item.getSeller().getId() != this.connectedUserId).collect(Collectors.toList());
				//System.out.println("search4buy filterByConnectedModeNotMe=" + items.size());
			} else {
				// disconnected mode => only "en cours" pending bid
				items = itemMngr.filterByStatus(items, Item.STATUS_PENDING);
				//System.out.println("search4buy filterByStatus=" + items.size());
			}

			List<Item> wonItems = new ArrayList<>();

			if ((saleIsOpen || isCurrentUser || saleIsWon) && this.connectedUserId > 0) {
				// connected mode
				if (saleIsWon) {
					wonItems = itemMngr.filterByBuyer(items, this.connectedUserId);
					//System.out.println("search4buy filterByBuyer=" + items.size());
					if (!saleIsOpen && !isCurrentUser) {
						return wonItems;
					}
				}
				items = itemMngr.filterByStatus(items, Item.STATUS_PENDING);
				//System.out.println("search4buy filterByStatus=" + items.size());
				if (isCurrentUser) {
					items = itemMngr.filterByBidsBuyer(items, this.connectedUserId);
					//System.out.println("search4buy filterByBidsBuyer=" + items.size());
				}
			}
			return Stream.of(items, wonItems).flatMap(Collection::stream).distinct().collect(Collectors.toList());
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
	@Path("/search4sell")
	public Object searchSales(@QueryParam("userSearch") String userSearch, @QueryParam("category") String category, @QueryParam("saleIsOnGoing") boolean saleIsOnGoing, @QueryParam("saleIsCreated") boolean saleIsCreated,
			@QueryParam("saleIsOver") boolean saleIsOver) {
		try {
			int connectedUserId = (int) request.getSession().getAttribute("connectedUserId");
			ItemManager itemMngr = new ItemManager();
			if (category.equals("null") || category.isEmpty()) {
				category = null;
			}
			List<Item> items = itemMngr.filterBySeller(itemMngr.getItemsLike(userSearch, category), connectedUserId);
			System.out.println("search4sell items=" + items.size());
			List<Item> openArticles = new ArrayList<>();
			List<Item> createdArticles = new ArrayList<>();
			List<Item> endedArticles = new ArrayList<>();
			if (!saleIsOnGoing && !saleIsCreated && !saleIsOver) {
				return items;
			}
			if (saleIsOnGoing) {
				openArticles = itemMngr.filterByStatus(items, Item.STATUS_PENDING);
			}
			if (saleIsCreated) {
				createdArticles = itemMngr.filterByStatus(items, Item.STATUS_CREATED);
			}
			if (saleIsOver) {
				endedArticles = itemMngr.filterByIsOver(items);
			}
			return Stream.of(openArticles, createdArticles, endedArticles).flatMap(Collection::stream).distinct().collect(Collectors.toList());
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
	@Path("/refresh")
	public Object refresh() {
		try {
			ItemManager itemMngr = new ItemManager();
			itemMngr.setAllItemsGained();
			return itemMngr.getTimeUntilNextEnd();
		} catch (BidsException e) {
			e.printStackTrace();
			return new HashMap<String, String>() {
				{
					put("message", e.getMessage());
				}
			};
		}
	}

	private void updatePickUpAdress(User seller, Item item, Map<String, Object> data) throws BidsException {
		String street, zipCode, town;
		boolean modifyStreet = !(street = (String) data.get("street")).equals(seller.getStreet());
		boolean modifyZipCode = !(zipCode = (String) data.get("zipCode")).equals(seller.getZipCode());
		boolean modifyTown = !(town = (String) data.get("town")).equals(seller.getTown());
		if (modifyStreet || modifyZipCode || modifyTown) {
			PickUpAdressManager pickUpAdressMngr = new PickUpAdressManager();
			PickUpAdress pickUpAdr = pickUpAdressMngr.getById(item.getId());
			System.out.println("updatePickUpAdress::pickUpAdr 1 =" + pickUpAdr);
			if (pickUpAdr == null) {
				pickUpAdr = new PickUpAdress(item, street, zipCode, town);
				pickUpAdressMngr.add(pickUpAdr);
				pickUpAdr = pickUpAdressMngr.getById(item.getId());
			}
			System.out.println("updatePickUpAdress::pickUpAdr 2 =" + pickUpAdr);
			pickUpAdr.setStreet(street);
			pickUpAdr.setZipCode(zipCode);
			pickUpAdr.setTown(town);
			pickUpAdressMngr.update(pickUpAdr);
		}
	}

}
