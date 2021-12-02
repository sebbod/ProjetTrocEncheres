package fr.eni.bids.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import fr.eni.bids.BidsException;
import fr.eni.bids.bo.Bid;
import fr.eni.bids.bo.Item;
import fr.eni.bids.bo.User;
import fr.eni.bids.dal.DAO;
import fr.eni.bids.dal.DAOFactory;
import fr.eni.bids.dal.jdbc.QueryGenerator;

public class BidManager extends GenericManager<Bid> {
	private final DAO<Bid> bidDao;
	private Bid temporaryHighestBid;

	public BidManager() throws BidsException {
		super();
		bidDao = DAOFactory.getBidDAO();
	}

	/**
	 * @param identifier
	 *            int | Identifier of the item.
	 * @return Bid | Bid with the highest bid.
	 * @throws BidsException
	 *             BidsException | BID_GET_HIGHEST_BID
	 */
	public Bid getHighestBid(int identifier) throws BidsException {
		try {
			return bidDao.selectBy(QueryGenerator.SELECT_BID_MAX(), Collections.singleton(identifier));
		} catch (BidsException e) {
			throw new BidsException(ErrorCodesBLL.BID_GET_HIGHEST_BID, e);
		}

	}

	/**
	 * Call the getHighestBid() method with an item as argument.
	 */
	public Bid getHighestBid(Item item) throws BidsException {
		return getHighestBid(item.getId());
	}

	/**
	 * Check if an instance of Bid has the highest bid.
	 * 
	 * @param bid
	 *            Bid
	 * @return boolean | "true" if the bid is the highest.
	 * @throws BidsException
	 *             BidsException
	 */
	public boolean isHighestBid(Bid bid) throws BidsException {
		return bid.getAmount() == getHighestBid(bid.getItem()).getAmount();
	}

	/**
	 * @param user
	 *            User
	 * @return List<Bid> | List of all the bids made by a user.
	 * @throws BidsException
	 *             BidsException | BID_GET_BIDS_FROM
	 */
	@SuppressWarnings("serial")
	public List<Bid> getBidsFrom(User user) throws BidsException {
		try {
			int userIdBuyer = user.getId();
			return bidDao.selectAllByFields(new HashMap<String, Object>() {
				{
					put("userIdBuyer", userIdBuyer);
				}
			});
		} catch (BidsException e) {
			throw new BidsException(ErrorCodesBLL.BID_GET_BIDS_FROM, e);
		}
	}

	/**
	 * Call the getBidsFrom() method with a given user, and filter the instances of Bid the user is actually winning.
	 */
	public List<Bid> getWinningBidsFrom(User user) throws BidsException {
		List<Bid> winningBids = new ArrayList<>();
		for (Bid bid : getBidsFrom(user)) {
			if (isHighestBid(bid)) {
				winningBids.add(bid);
			}
		}
		return winningBids;
	}

	/**
	 * @param item
	 *            Item | Item the bids to delete are on.
	 * @param updateCredits
	 *            boolean | "true" if the credits of the user needs to be updated after deletion. "false" otherwise. Must be "false" while deleting all the bids when the sale ends since the winning user must not get his/her credits back.
	 * @throws BidsException
	 *             BidsException | BID_DELETE_ALL_BY
	 */
	public void deleteAllBy(Item item, boolean updateCredits) throws BidsException {
		try {
			this.temporaryHighestBid = getHighestBid(item);
			/*
			 * Note: A loop is not necessary, the table having two identifiers, and the
			 * first one being itemId (selling item). The SQL Query will be executed with only the
			 * identifier for the item, and delete all the matching occurrences.
			 */
			bidDao.delete(item.getId());
			if (updateCredits) {
				setCredits(this.temporaryHighestBid, null);
			}
		} catch (BidsException e) {
			throw new BidsException(ErrorCodesBLL.BID_DELETE_ALL_BY, e);
		}
	}

	/**
	 * Delete all the bids on a given item, updating the credits of the user.
	 */
	public void deleteAllBy(Item item) throws BidsException {
		deleteAllBy(item, true);
	}

	/**
	 * Delete all the bids made by a user.
	 * 
	 * @param user
	 *            User | User to delete the bids from.
	 * @throws BidsException
	 *             BidsException | BID_DELETE_ALL_BY
	 */
	public void deleteAllBy(User user) throws BidsException {
		try {
			List<Bid> bids = getBidsFrom(user);
			/*
			 * Note: While deleting a bid from a user, the delete() method is checking if it
			 * is the highest one. If it is, the next one with a user having enough credits
			 * will be the new highest one. The other ones (before that) with not enough
			 * credits will be deleted as well.
			 */
			for (Bid bid : bids) {
				delete(bid);
			}
		} catch (BidsException e) {
			throw new BidsException(ErrorCodesBLL.BID_DELETE_ALL_BY, e);
		}
	}

	/**
	 * Delete all the bids on an item when the sale is over and set the highest one as the buyer of the item.
	 */
	public void deleteAllWhenOver(Item item) throws BidsException {
		if (item.getDateEnd().isBefore(LocalDateTime.now())) {
			item.setBuyer(getHighestBid(item).getBuyer());
			item.setStatus();
			new ItemManager().update(item);
			deleteAllBy(item, false);
		}
	}

	@Override
	public Bid add(Bid bid) throws BidsException {
		this.temporaryHighestBid = getHighestBid(bid.getItem());
		return super.add(bid);
	}

	@Override
	public Bid update(Bid bid) throws BidsException {
		this.temporaryHighestBid = getHighestBid(bid.getItem());
		return super.update(bid);
	}

	// LOGIC & CHECKS

	/**
	 * Get an array of the identifiers values for a given Bid.
	 */
	@Override
	protected int[] getIdentifiers(Bid bid) {
		return new int[] { bid.getItemId(), bid.getUserIdBuyer() };
	}

	@Override
	protected void executeUpdate(Bid bid, String operationCRUD) throws BidsException {
		if (!operationCRUD.equals("DELETE")) {
			setCredits(this.temporaryHighestBid, bid);
		}
		/*
		 * If the highest bid is deleted, the credits for the next highest one are
		 * adjusted. If the credits are not enough, that bid is also deleted, and the
		 * operation is repeated on the next one until one with enough credits is found.
		 */
		else if (bid.getAmount() == this.temporaryHighestBid.getAmount()) {
			setCredits(null, getHighestBid(bid.getItem()));
		}
	}

	protected void checkAttributes(Bid bid) throws BidsException {
		if (bid == null) {
			throw new BidsException(ErrorCodesBLL.BO_NULL_ERROR.get("Bid"));
		}
		StringBuilder errors = new StringBuilder();
		if (bid.getItem() == null) {
			errors.append("Champs obligatoire. L'enchère n'a pas d'article associé.").append("\n");
		}
		if (bid.getBuyer() == null) {
			errors.append("Champs obligatoire. L'enchère n'a pas d'utilisateur associé.").append("\n");
		}
		if (bid.getDateCreated() == null) {
			errors.append("Champs obligatoire. L'enchère n'a pas de date associée").append("\n");
		}
		if (bid.getDateCreated().isAfter(bid.getItem().getDateEnd())) {
			errors.append("Champs incorrect. L'enchère sur l'article associée est déjà terminée.").append("\n");
		}
		if (this.temporaryHighestBid != null && bid.getAmount() > this.temporaryHighestBid.getAmount()) {
			errors.append("Champs incorrect. Le montant de l'enchère doit être supérieur à l'enchère actuelle la plus haute.").append("\n");
		}
		if (bid.getAmount() > bid.getBuyer().getCredit()) {
			errors.append("Champs incorrect. L'utilisateur ne dispose pas de suffisamment de crédits.").append("\n");
		}
		if (!errors.toString().isEmpty()) {
			throw new BidsException(errors.toString());
		}
	}

	protected boolean exist(Bid bid) throws BidsException {
		return getById(bid.getItemId(), bid.getUserIdBuyer()) != null;
	}

	/**
	 * Adjust and update the users credits.
	 * 
	 * @param bidPlus
	 *            Bid | Bid of the user the credits will to be added to.
	 * @param bidMinus
	 *            Bid | Bid of the user the credits will to be subtracted from.
	 * @throws BidsException
	 *             BidsException
	 */
	private void setCredits(Bid bidPlus, Bid bidMinus) throws BidsException {
		UserManager userMng = new UserManager();
		if (bidPlus != null) {
			bidPlus.getBuyer().addCredit(bidPlus.getAmount());
			userMng.update(bidPlus.getBuyer());
		}
		if (bidMinus != null && bidMinus.getBuyer().getCredit() >= bidMinus.getAmount()) {
			bidMinus.getBuyer().substractCredit(bidMinus.getAmount());
			userMng.update(bidMinus.getBuyer());
		} else if (bidMinus != null) {
			delete(bidMinus);
		}
	}
}
