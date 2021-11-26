package fr.eni.bids.bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.bids.BidsException;
import fr.eni.bids.bo.Category;
import fr.eni.bids.bo.Item;
import fr.eni.bids.dal.DAO;
import fr.eni.bids.dal.DAOFactory;

public class CategoryManager extends GenericManager<Category> {
	private final DAO<Category> CategoryDao;

	// CONSTRUCTOR
	public CategoryManager() throws BidsException {
		super();
		this.CategoryDao = DAOFactory.getCategoryDAO();
	}

	// LOGIC & CHECKS

	/**
	 * Get an array of the identifiers values for a given Category.
	 */
	@Override
	protected int[] getIdentifiers(Category categorie) {
		return new int[] { categorie.getId() };
	}

	/**
	 * Executed after the operation.
	 */
	@Override
	protected void executeUpdate(Category categorie, String operationCRUD) throws BidsException {
		Map<String, Object> fields = new HashMap<String, Object>() {
			{
				put("categorie", categorie.getId());
			}
		};
		List<Item> items = DAOFactory.getItemDAO().selectAllByFields(fields);
		for (Item item : items) {
			item.setCategory(null);
			new ItemManager().update(item);
		}
		;
	}

	/**
	 * Check all the attributes of a category.
	 * 
	 * @param categorie
	 *            Category | Category to check.
	 * @throws BidsException
	 *             BidsException | Newly created exception.
	 */
	protected void checkAttributes(Category categorie) throws BidsException {
		if (categorie == null) {
			throw new BidsException(ErrorCodesBLL.BO_NULL_ERROR.get("Category"));
		}
		if (categorie.getLibelle() == null || categorie.getLibelle().isEmpty()) {
			throw new BidsException("Champs obligatoire. La catégorie n'a pas de libellé.");
		}
	}

	/**
	 * Check if an article already exists in the database.
	 */
	protected boolean exist(Category categorie) throws BidsException {
		return CategoryDao.selectByField("libelle", categorie.getLibelle()) != null;
	}

}
