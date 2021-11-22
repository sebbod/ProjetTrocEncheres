package fr.eni.bids.dal;

import java.util.List;

import fr.eni.bids.bo.Item;

public interface ItemDAO extends DAO<Item> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.Items.dal.DAO#getById(java.lang.Integer)
	 */
	@Override
	public Item getById(Integer id) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.Items.dal.DAO#getAll()
	 */
	@Override
	public List<Item> getAll() throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.Items.dal.DAO#insert(java.lang.Object)
	 */
	@Override
	public void insert(Item data) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.Items.dal.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(Item data) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.Items.dal.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Item obj) throws DALException;

}
