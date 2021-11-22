package fr.eni.bids.bo;

import java.time.LocalDateTime;

public class Bid {

	private int userId;
	private int itemId;
	private LocalDateTime dateCreated;
	private int amount;

	private User seller;
	private Item item;

	/**
	 * @param userId
	 * @param itemId
	 * @param dateCreated
	 * @param amount
	 */
	public Bid(int userId, int itemId, LocalDateTime dateCreated, int amount) {
		super();
		this.userId = userId;
		this.itemId = itemId;
		this.dateCreated = dateCreated;
		this.amount = amount;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the dateCreated
	 */
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated
	 *            the dateCreated to set
	 */
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the seller
	 */
	public User getSeller() {
		return seller;
	}

	/**
	 * @param seller
	 *            the seller to set
	 */
	public void setSeller(User seller) {
		this.seller = seller;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

}
