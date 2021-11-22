package fr.eni.bids.bo;

public class PickUpAdress {
	private Integer id;
	private String street;
	private String city;
	private String postCode;

	/**
	 * @param id
	 * @param street
	 * @param city
	 * @param postCode
	 */
	public PickUpAdress(Integer id, String street, String city, String postCode) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.postCode = postCode;
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
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode
	 *            the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}
