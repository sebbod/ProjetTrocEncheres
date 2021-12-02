package fr.eni.bids.bo;

public class PickUpAdress {
	private String street;
	private String town;
	private String zipCode;
	private Item item;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PickUpAdress [itemId=" + getItemId() + ", street=" + street + ", town=" + town + ", zipCode=" + zipCode + ", item=" + item + "]";
	}

	public PickUpAdress() {
	}

	public PickUpAdress(Item item) {
		setItemId(item);
		User seller = item.getSeller();
		setStreet(seller.getStreet());
		setZipCode(seller.getZipCode());
		setTown(seller.getTown());
	}

	public PickUpAdress(Item item, String street, String zipCode, String town) {
		setItemId(item);
		setStreet(street);
		setZipCode(zipCode);
		setTown(town);
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
	 * @return the itemId
	 */
	public int getItemId() {
		return this.item != null ? this.item.getId() : 0;
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
