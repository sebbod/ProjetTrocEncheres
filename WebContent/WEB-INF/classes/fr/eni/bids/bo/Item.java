package fr.eni.bids.bo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Item implements Serializable {
	private static final long serialVersionUID = -4813349171372997274L;

	public static String STATUS_CREATED = "Créée";
	public static String STATUS_CANCELED = "Annulée";
	public static String STATUS_PENDING = "En cours";
	public static String STATUS_CLOSED = "Terminée";
	public static String STATUS_COLLECTED = "Retirée";

	private Integer id;
	private String name;
	private String description;
	private LocalDateTime dateStart;
	private LocalDateTime dateEnd;
	private int priceSeller;
	private int priceBuyer;
	private String status;
	private User seller;
	private User buyer;
	private Category category;
	private boolean isCollected;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", priceSeller=" + priceSeller + ", priceBuyer=" + priceBuyer + ", status=" + status + ", seller="
				+ seller + ", buyer=" + buyer + ", category=" + category + ", isCollected=" + isCollected + "]";
	}

	public Item() {
	}

	public Item(String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd, User seller) {
		setName(name);
		setDescription(description);
		setDateStart(dateStart == null ? LocalDateTime.now() : dateStart);
		setDateEnd(dateEnd);
		setUserIdSeller(seller);
		setStatus();
	}

	public Item(String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd, User seller, Category category) {
		this(name, description, dateStart, dateEnd, seller);
		setCateId(category);
	}

	public Item(String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd, int priceSeller, User seller, Category category) {
		this(name, description, dateStart, dateEnd, seller, category);
		setPriceSeller(priceSeller);
	}

	public Item(int id, String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd, int priceSeller, User seller, Category category) {
		this(name, description, dateStart, dateEnd, priceSeller, seller, category);
		setId(id);
	}

	public Item(int id, String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd, int priceSeller, int priceBuyer, User seller, Category category) {
		this(id, name, description, dateStart, dateEnd, priceSeller, seller, category);
		setPriceBuyer(priceBuyer);
	}

	public Item(int id, String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd, int priceSeller, int priceBuyer, User seller, User buyer, Category category) {
		this(id, name, description, dateStart, dateEnd, priceSeller, priceBuyer, seller, category);
		setUserIdBuyer(buyer);
	}

	public Item(int id, String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd, int priceSeller, int priceBuyer, User seller, User buyer, Category category, boolean isCollected) {
		this(id, name, description, dateStart, dateEnd, priceSeller, priceBuyer, seller, buyer, category);
		setIsCollected(isCollected);
	}

	public Item(String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd, int priceSeller, int priceBuyer, User seller, User buyer, Category category) {
		this(name, description, dateStart, dateEnd, seller, category);
		setPriceSeller(priceSeller);
		setPriceBuyer(priceBuyer);
		setUserIdBuyer(buyer);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the dateStart
	 */
	public LocalDateTime getDateStart() {
		return dateStart;
	}

	/**
	 * @param dateStart
	 *            the dateStart to set
	 */
	public void setDateStart(LocalDateTime dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * @return the dateEnd
	 */
	public LocalDateTime getDateEnd() {
		return dateEnd;
	}

	/**
	 * @param dateEnd
	 *            the dateEnd to set
	 */
	public void setDateEnd(LocalDateTime dateEnd) {
		this.dateEnd = dateEnd;
		setStatus();
	}

	/**
	 * @return the priceSeller
	 */
	public int getPriceSeller() {
		return priceSeller;
	}

	/**
	 * @param priceSeller
	 *            the priceSeller to set
	 */
	public void setPriceSeller(int priceSeller) {
		this.priceSeller = priceSeller;
	}

	/**
	 * @return the priceBuyer
	 */
	public int getPriceBuyer() {
		return priceBuyer;
	}

	/**
	 * @param priceBuyer
	 *            the priceBuyer to set
	 */
	public void setPriceBuyer(int priceBuyer) {
		this.priceBuyer = priceBuyer;
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
	public void setUserIdSeller(User seller) {
		if (seller != null)
			seller.setPwd("");
		this.seller = seller;
	}

	/**
	 * @return the seller user id
	 */
	public int getUserIdSeller() {
		return this.seller != null ? this.seller.getId() : 0;
	}

	/**
	 * @return the buyer
	 */
	public User getBuyer() {
		return buyer;
	}

	/**
	 * @param buyer
	 *            the buyer to set
	 */
	public void setUserIdBuyer(User buyer) {
		if (buyer != null)
			buyer.setPwd("");
		this.buyer = buyer;
	}

	/**
	 * @return the buyer user id
	 */
	public int getUserIdBuyer() {
		return this.buyer != null ? this.buyer.getId() : 0;
	}

	/**
	 * @return the category
	 */
	public Category getCateId() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCateId(Category category) {
		this.category = category;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		//setStatus();
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * The status of a sale is automatically set according to the dates.
	 */
	public void setStatus() {
		if (this.dateStart.isAfter(LocalDateTime.now())) {
			this.status = Item.STATUS_CREATED;
		} else if (this.dateEnd.isBefore(LocalDateTime.now())) {
			this.status = this.isCollected ? Item.STATUS_COLLECTED : Item.STATUS_CLOSED;
		} else {
			this.status = Item.STATUS_PENDING;
		}
	}

	/**
	 * @return the isCollected
	 */
	public boolean getIsCollect() {
		return isCollected;
	}

	/**
	 * @param isWithdrawn
	 *            the isWithdrawn to set
	 */
	public void setIsCollected(boolean isCollect) {
		this.isCollected = isCollect;
	}

}
