package fr.eni.bids.dal;

import java.util.List;

import fr.eni.bids.bo.PickUpAdress;

public interface PickUpAdressDAO extends DAO<PickUpAdress> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.PickUpAdresss.dal.DAO#getById(java.lang.Integer)
	 */
	@Override
	public PickUpAdress getById(Integer id) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.PickUpAdresss.dal.DAO#getAll()
	 */
	@Override
	public List<PickUpAdress> getAll() throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.PickUpAdresss.dal.DAO#insert(java.lang.Object)
	 */
	@Override
	public void insert(PickUpAdress data) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.PickUpAdresss.dal.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(PickUpAdress data) throws DALException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.eni.PickUpAdresss.dal.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Integer id) throws DALException;

}
