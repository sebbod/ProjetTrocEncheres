package fr.eni.bids.bll;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.eni.bids.BidsException;
import fr.eni.bids.bo.Bid;
import fr.eni.bids.bo.Item;
import fr.eni.bids.bo.PickUpAdress;
import fr.eni.bids.bo.User;
import fr.eni.bids.dal.DAO;
import fr.eni.bids.dal.DAOFactory;
import fr.eni.bids.dal.jdbc.QueryGenerator;

public class ItemManager extends GenericManager<Item> {
	private final DAO<Item> ItemDao;

	public ItemManager() throws BidsException {
		super();
		ItemDao = DAOFactory.getItemDAO();
	}

	// CRUD

	/**
	 * Delete all the items from a given seller.
	 */
	void deleteAllBySeller(User u) throws BidsException {
		List<Item> items = getItemsFromSeller(u);
		for (Item item : items) {
			delete(item);
		}
	}

	/**
	 * @param field
	 *            String | "seller".
	 * @param u
	 *            User
	 * @return List<Item> | List of items for a given user.
	 * @throws BidsException
	 *             BidsException | ITEM_GET_ALL_FROM_ERROR
	 */
	private List<Item> getItemsFrom(String field, User u) throws BidsException {
		int userId = u.getId();
		try {
			return this.ItemDao.selectAllByField(field, userId);
		} catch (BidsException e) {
			e.printStackTrace();
			throw new BidsException(ErrorCodesBLL.ITEM_GET_ALL_FROM_ERROR, e);
		}
	}

	/**
	 * Call getItemsFrom with "seller".
	 */
	public List<Item> getItemsFromSeller(User seller) throws BidsException {
		return getItemsFrom("userIdSeller", seller);
	}

	/**
	 * @param variable
	 *            String | Search input for the name or description of a user.
	 * @return List<Item> | List of items including part of the variable in their name or description.
	 * @throws BidsException
	 *             BidsException |
	 */
	public List<Item> getItemsLike(String variable, String category) throws BidsException {
		try {
			return ItemDao.selectAllBy(QueryGenerator.SELECT_ITEM_LIKE(variable, category), null);
		} catch (BidsException e) {
			throw new BidsException(ErrorCodesBLL.ITEM_GET_ALL_BY_NAME_DESCRIPTION, e);
		}
	}

	/**
	 * Update the items won for a given user.
	 */
	public void setItemsGained(User u) throws BidsException {
		try {
			BidManager bidMngr = new BidManager();
			for (Bid bid : bidMngr.getWinningBidsFrom(u)) {
				Item item = bid.getItem();
				if (item.getStatus().equals(Item.STATUS_CLOSED)) {
					item.setPriceBuyer(bid.getAmount());
					item.setBuyer(u);
					bidMngr.deleteAllWhenOver(item);
				}
				update(item);
			}
		} catch (BidsException e) {
			throw new BidsException(ErrorCodesBLL.ITEM_SET_ITEM_WON, e);
		}

	}

	/**
	 * Update the items won for all the users.
	 */
	public void setAllItemsGained() throws BidsException {
		for (User u : new UserManager().getAll()) {
			setItemsGained(u);
		}
	}

	/**
	 * @return long | Time in milliseconds until the next sale is ending.
	 */
	public int getTimeUntilNextEnd() throws BidsException {
		setAllItemsGained();
		int milliseconds = Integer.MAX_VALUE;
		for (Item item : getAll()) {
			if (item.getStatus().equals(Item.STATUS_PENDING)) {
				milliseconds = (int) Math.min(milliseconds, Duration.between(LocalDateTime.now(), item.getDateEnd()).toMillis());
			}
		}
		return milliseconds;
	}

	/**
	 * Set an article as having been picked up.
	 */
	public void setCollected(Item item) throws BidsException {
		item.setIsCollected(true);
		PickUpAdressManager pickUpAdrMngr = new PickUpAdressManager();
		PickUpAdress pickUpAdr = pickUpAdrMngr.getById(item.getId());
		pickUpAdrMngr.delete(pickUpAdr);
		update(item);
	}

	// FILTERS

	/**
	 * Filter a list of items by status.
	 */
	public List<Item> filterByStatus(List<Item> items, String status) {
		return items.stream().filter(i -> i.getStatus().equals(status)).collect(Collectors.toList());
	}

	/**
	 * Filter a list of items by category.
	 */
	public List<Item> filterByCategorie(List<Item> items, String category) {
		return items.stream().filter(item -> item.getCateId().getLibelle().equals(category)).collect(Collectors.toList());
	}

	/**
	 * Filter a list of items by buyer.
	 */
	public List<Item> filterByBuyer(List<Item> items, int userId) {
		return items.stream().filter(i -> i.getBuyer() != null && i.getBuyer().getId() == userId).collect(Collectors.toList());
	}

	/**
	 * Filter a list of items by bids's buyer.
	 */
	public List<Item> filterByBidsBuyer(List<Item> items, int userId) {
		return items.stream().filter(i -> {
			try {
				return new BidManager().getById(i.getId(), userId) != null;
			} catch (BidsException e) {
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList());
	}

	/**
	 * Filter the items for which the sales are over.
	 */
	public List<Item> filterByIsOver(List<Item> items) {
		return items.stream().filter(item -> item.getDateEnd().isBefore(LocalDateTime.now())).collect(Collectors.toList());
	}

	/**
	 * Filter the items by seller
	 */
	public List<Item> filterBySeller(List<Item> items, int userIdSeller) throws BidsException {
		return items.stream().filter(item -> item.getSeller().getId() == userIdSeller).collect(Collectors.toList());
	}

	// LOGIC & CHECKS

	/**
	 * Get an array of the identifiers values for a given Item.
	 */
	@Override
	protected int[] getIdentifiers(Item item) {
		return new int[] { item.getId() };
	}

	/**
	 * Check all the attributes of an item.
	 * 
	 * @param item
	 *            Item | Item to check.
	 * @throws BidsException
	 *             BidsException | Newly created exception.
	 */
	protected void checkAttributes(Item item) throws BidsException {
		if (item == null) {
			throw new BidsException(ErrorCodesBLL.BO_NULL_ERROR.get("Item"));
		}
		StringBuilder errors = new StringBuilder();
		if (item.getName() == null || item.getName().isEmpty()) {
			errors.append("Champs obligatoire. L'article n'a pas de nom.").append("\n");
		}
		if (item.getDescription() == null || item.getDescription().isEmpty()) {
			errors.append("Champs obligatoire. L'article n'a pas de description.").append("\n");
		}
		if (item.getDateStart() == null) {
			item.setDateStart(LocalDateTime.now());
		}
		if (item.getDateEnd() == null) {
			errors.append("Champs obligatoire. L'article n'a pas de date de fin d'enchère.").append("\n");
		}
		LocalDateTime dateFinBids = item.getDateEnd();
		if (dateFinBids.isBefore(LocalDateTime.now()) || dateFinBids.isBefore(item.getDateStart())) {
			errors.append("Champs incorrecte. La date de fin d'enchère est invalide.").append("\n");
		}
		if (item.getSeller() == null) {
			errors.append("Champs obligatoire. L'article n'a pas de vendeur.").append("\n");
		}
		if (item.getCateId() != null && new CategoryManager().getById(item.getId()) == null) {
			errors.append("Champs incorrect. La catégorie renseignée n'existe pas.").append("\n");
		}
		if (item.getPriceSeller() < 0) {
			errors.append("Champs incorrect. La mise à prix ne peut pas être négative.").append("\n");
		}
		if (item.getPriceBuyer() < 0) {
			errors.append("Champs incorrect. Le prix de vente ne peut pas être négatif").append("\n");
		}
		if (!errors.toString().isEmpty()) {
			throw new BidsException(errors.toString());
		}
	}

	/**
	 * Check if an item already exists in the database.
	 */
	protected boolean exist(Item item) throws BidsException {
		@SuppressWarnings("serial")
		Map<String, Object> fields = new HashMap<String, Object>() {
			{
				put("name", item.getName());
				put("description", item.getDescription());
			}
		};
		return ItemDao.selectByFields(fields) != null;
	}

	@Override
	protected void executeUpdate(Item object, String operationCRUD) throws BidsException {
		// TODO
	}
}
