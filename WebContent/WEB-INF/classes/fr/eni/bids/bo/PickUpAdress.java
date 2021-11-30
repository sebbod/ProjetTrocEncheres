package fr.eni.bids.bo;

public class PickUpAdress {
	private Integer itemId;
	private String street;
	private String town;
	private String zipCode;
	private Item item;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PickUpAdress [itemId=" + itemId + ", street=" + street + ", town=" + town + ", zipCode=" + zipCode + ", item=" + item + "]";
	}

	public PickUpAdress() {
	}

	public PickUpAdress(Item item) {
		setItem(item);
		setItemId(item.getId());

		User seller = item.getUserIdSeller();
		setStreet(seller.getStreet());
		setZipCode(seller.getZipCode());
		setTown(seller.getTown());
	}

	public PickUpAdress(Item item, String street, String zipCode, String town) {
		setItem(item);
		setItemId(item.getId());

		setStreet(street);
		setZipCode(zipCode);
		setTown(town);
	}

	/**
	 * @param id
	 * @param street
	 * @param town
	 * @param zipCode
	 */
	public PickUpAdress(Integer itemId, String street, String zipCode, String town) {
		super();
		this.itemId = itemId;
		this.street = street;
		this.zipCode = zipCode;
		this.town = town;
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

	/**
	 * @return the itemId
	 */
	public Integer getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @param town
	 *            the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
