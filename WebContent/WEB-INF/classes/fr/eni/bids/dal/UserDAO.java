package fr.eni.bids.dal;

import java.util.List;

import fr.eni.bids.bo.User;

public interface UserDAO extends DAO<User> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.bids.dal.DAO#getById(java.lang.Integer)
	 */
	@Override
	public User getById(Integer id) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.bids.dal.DAO#getAll()
	 */
	@Override
	public List<User> getAll() throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.bids.dal.DAO#insert(java.lang.Object)
	 */
	@Override
	public void insert(User data) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.bids.dal.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(User data) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.bids.dal.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(User obj) throws DALException;

}
