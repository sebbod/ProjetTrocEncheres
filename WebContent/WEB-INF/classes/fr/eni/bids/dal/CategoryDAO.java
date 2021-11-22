package fr.eni.bids.dal;

import java.util.List;

import fr.eni.bids.bo.Category;

public interface CategoryDAO extends DAO<Category> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.Categorys.dal.DAO#getById(java.lang.Integer)
	 */
	@Override
	public Category getById(Integer id) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.Categorys.dal.DAO#getAll()
	 */
	@Override
	public List<Category> getAll() throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.Categorys.dal.DAO#insert(java.lang.Object)
	 */
	@Override
	public void insert(Category data) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.Categorys.dal.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(Category data) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.Categorys.dal.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Category obj) throws DALException;

}
