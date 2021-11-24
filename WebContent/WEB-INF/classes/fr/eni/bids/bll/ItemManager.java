package fr.eni.bids.bll;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.eni.bids.BidsException;
import fr.eni.bids.bo.Bid;
import fr.eni.bids.bo.Item;
import fr.eni.bids.bo.User;
import fr.eni.bids.dal.DAO;
import fr.eni.bids.dal.DAOFactory;
import fr.eni.bids.dal.jdbc.QueryGenerator;

public class ItemManager extends GenericManager<Item> {
	private static DAO<Item> ItemDao;

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
		} catch (BidsException eException) {
			eException.printStackTrace();
			throw new BidsException(ErrorCodesBLL.ITEM_GET_ALL_FROM_ERROR, eException);
		}
	}

	/**
	 * Call getItemsFrom with "seller".
	 */
	public List<Item> getItemsFromSeller(User seller) throws BidsException {
		return getItemsFrom("seller", seller);
	}

	/**
	 * @param variable
	 *            String | Search input for the name or description of a user.
	 * @return List<Item> | List of items including part of the variable in their
	 *         name or description.
	 * @throws BidsException
	 *             BidsException |
	 */
	public List<Item> getItemsLike(String variable, String category) throws BidsException {
		try {
			return ItemDao.selectAllBy(QueryGenerator.SELECT_ITEM_LIKE(variable, category), null);
		} catch (BidsException eException) {
			throw new BidsException(ErrorCodesBLL.ITEM_GET_ALL_BY_NAME_DESCRIPTION, eException);
		}
	}

	/**
	 * Update the items won for a given user.
	 */
	public void setItemsObtenus(User u) throws BidsException {
		try {
			BidManager enchereManager = new BidManager();
			for (Bid enchere : enchereManager.getWinningBidsFrom(u)) {
				Item item = enchere.getItem();
				// TODO
				/*
				 * String etatVente = item.getEtatVente(); if
				 * (etatVente.equals("Enchères terminées")) {
				 * item.setPrixVente(enchere.getMontantBid()); item.setAcquereur(u);
				 * enchereManager.deleteAllWhenOver(item); }
				 */
				update(item);
			}
		} catch (BidsException eException) {
			throw new BidsException(ErrorCodesBLL.ITEM_SET_ITEM_WON, eException);
		}

	}

	/**
	 * Update the items won for all the users.
	 */
	public void setAllItemsObtenus() throws BidsException {
		for (User u : new UserManager().getAll()) {
			setItemsObtenus(u);
		}
	}

	// FILTERS

	/**
	 * Filter a list of items by category.
	 */
	public List<Item> filterByCategorie(List<Item> items, String category) {
		return items.stream().filter(item -> item.getCategory().getLibelle().equals(category))
				.collect(Collectors.toList());
	}

	/**
	 * Filter the items for which the sales are over.
	 */
	public List<Item> filterByIsOver(List<Item> items) {
		return items.stream().filter(item -> item.getDateEnd().isBefore(LocalDateTime.now()))
				.collect(Collectors.toList());
	}

	/**
	 * Filter the items for which the sales are over.
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
		if (item.getCategory() != null && new CategoryManager().getById(item.getId()) == null) {
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
	protected boolean checkUnity(Item item) throws BidsException {
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
