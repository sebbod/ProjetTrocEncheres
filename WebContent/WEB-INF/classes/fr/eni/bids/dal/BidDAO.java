package fr.eni.bids.dal;

import java.util.List;

import fr.eni.bids.bo.Bid;

public interface BidDAO extends DAO<Bid> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.bids.dal.DAO#getById(java.lang.Integer)
	 */
	@Override
	public Bid getById(Integer id) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.bids.dal.DAO#getAll()
	 */
	@Override
	public List<Bid> getAll() throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.bids.dal.DAO#insert(java.lang.Object)
	 */
	@Override
	public void insert(Bid data) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.bids.dal.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(Bid data) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.bids.dal.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Integer id) throws DALException;

}
