package fr.eni.bids.bo;

import java.time.LocalDateTime;

public class Bid {
	private LocalDateTime dateCreated;
	private int amount;

	private User buyer;
	private Item item;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bid [userIdBuyer=" + getUserIdBuyer() + ", itemId=" + getItemId() + ", dateCreated=" + dateCreated + ", amount=" + amount + ", buyer=" + buyer + ", item=" + item + "]";
	}

	public Bid() {
	}

	public Bid(User buyer, Item item, int amount) {
		setUserIdBuyer(buyer);
		setItemId(item);
		setDateCreated(LocalDateTime.now());
		setAmount(amount);
	}

	public Bid(User buyer, Item item, LocalDateTime dateCreated, int amount) {
		this(buyer, item, amount);
		setDateCreated(dateCreated);
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
	 * @return the user Buyer
	 */
	public User getBuyer() {
		return buyer;
	}

	/**
	 * @param user
	 *            Buyer the user Buyer to set
	 */
	public void setUserIdBuyer(User buyer) {
		if (buyer != null)
			buyer.setPwd("");
		this.buyer = buyer;
	}

	/**
	 * @return the buyer
	 */
	public int getUserIdBuyer() {
		return this.buyer != null ? this.buyer.getId() : 0;
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
	public void setItemId(Item item) {
		this.item = item;
	}

	/**
	 * @return the item
	 */
	public int getItemId() {
		return this.item != null ? this.item.getId() : 0;
	}

}
