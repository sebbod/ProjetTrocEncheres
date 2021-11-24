package fr.eni.bids.bo;

public class Category {
	private Integer id;
	private String libelle;

	public Category() {
	}

	/**
	 * @param id
	 * @param libelle
	 */
	public Category(Integer id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
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
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle
	 *            the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
