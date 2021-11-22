package fr.eni.bids.bo;

public class Category {
	private static final long serialVersionUID = -4813349171372997273L;

	private Integer id;
	private String libelle;

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
