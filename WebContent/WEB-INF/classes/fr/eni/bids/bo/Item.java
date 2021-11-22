package fr.eni.bids.bo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Item implements Serializable {
	private static final long serialVersionUID = -4813349171372997274L;

	private Integer id;
	private String name;
	private String description;
	private LocalDateTime dateStart;
	private LocalDateTime dateEnd;
	private int priceSeller;
	private int priceBuyer;
	private int userIdBuyer;
	private int cateId;
	private User buyer;
	private Category category;

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param dateStart
	 * @param dateEnd
	 * @param priceSeller
	 * @param priceBuyer
	 * @param userIdBuyer
	 * @param cateId
	 */
	public Item(Integer id, String name, String description, LocalDateTime dateStart, LocalDateTime dateEnd,
			int priceSeller, int priceBuyer, int userIdBuyer, int cateId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.priceSeller = priceSeller;
		this.priceBuyer = priceBuyer;
		this.userIdBuyer = userIdBuyer;
		this.cateId = cateId;
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
	 * @return the userIdBuyer
	 */
	public int getUserIdBuyer() {
		return userIdBuyer;
	}

	/**
	 * @param userIdBuyer
	 *            the userIdBuyer to set
	 */
	public void setUserIdBuyer(int userIdBuyer) {
		this.userIdBuyer = userIdBuyer;
	}

	/**
	 * @return the cateId
	 */
	public int getCateId() {
		return cateId;
	}

	/**
	 * @param cateId
	 *            the cateId to set
	 */
	public void setCateId(int cateId) {
		this.cateId = cateId;
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
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

}
