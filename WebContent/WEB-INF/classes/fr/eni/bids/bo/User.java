package fr.eni.bids.bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
public class User implements Serializable {
	private static final long serialVersionUID = 3578867045904313028L;
	private Integer id;
	private String pseudo;
	private String name;
	private String firstName;
	private String email;
	private String telephone;
	private String street;
	private String zipCode;
	private String town;
	private String pwd;
	private int credit;
	private byte isAdmin;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", pseudo=" + pseudo + ", name=" + name + ", firstName=" + firstName + ", email="
				+ email + ", telephone=" + telephone + ", street=" + street + ", zipCode=" + zipCode + ", town=" + town
				+ ", pwd=" + pwd + ", credit=" + credit + ", isAdmin=" + isAdmin + "]";
	}

	public User() {
	}

	public User(String pseudo, String name, String firstName, String email, String street, String zipCode,
			String town) {
		setPseudo(pseudo);
		setName(name);
		setFirstName(firstName);
		setEmail(email);
		setStreet(street);
		setZipCode(zipCode);
		setTown(town);
		setCredit(0);
		setIsAdmin((byte) 0);
	}

	public User(String pseudo, String name, String firstName, String email, String telephone, String street,
			String zipCode, String town) {
		this(pseudo, name, firstName, email, street, zipCode, town);
		setTelephone(telephone);
	}

	public User(String pseudo, String name, String firstName, String email, String telephone, String street,
			String zipCode, String town, String pwd) {
		this(pseudo, name, firstName, email, telephone, street, zipCode, town);
		setPwd(pwd);
	}

	public User(String pseudo, String name, String firstName, String email, String telephone, String street,
			String zipCode, String town, String pwd, int credit) {
		this(pseudo, name, firstName, email, telephone, street, zipCode, town, pwd);
		setCredit(credit);
	}

	public User(String pseudo, String name, String firstName, String email, String street, String zipCode, String town,
			String pwd, int credit, byte isAdmin) {
		this(pseudo, name, firstName, email, street, zipCode, town);
		setPwd(pwd);
		setCredit(credit);
		setIsAdmin(isAdmin);
	}

	public User(String pseudo, String name, String firstName, String email, String telephone, String street,
			String zipCode, String town, String pwd, int credit, byte isAdmin) {
		this(pseudo, name, firstName, email, street, zipCode, town, pwd, credit, isAdmin);
		setTelephone(telephone);
	}

	public User(int id, String pseudo, String name, String firstName, String email, String telephone, String street,
			String zipCode, String town, String pwd, int credit, byte isAdmin) {
		this(pseudo, name, firstName, email, telephone, street, zipCode, town, pwd, credit, isAdmin);
		setId(id);
	}

	// METHODS

	public void addCredit(int credit) {
		this.credit += credit;
	}

	public void substractCredit(int credit) {
		this.credit -= credit;
	}

	// GETTERS & SETTERS

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
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo
	 *            the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd
	 *            the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return the credit
	 */
	public int getCredit() {
		return credit;
	}

	/**
	 * @param credit
	 *            the credit to set
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}

	/**
	 * @return the isAdmin
	 */
	public byte getIsAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin
	 *            the isAdmin to set
	 */
	public void setIsAdmin(byte isAdmin) {
		this.isAdmin = isAdmin;
	}

}
