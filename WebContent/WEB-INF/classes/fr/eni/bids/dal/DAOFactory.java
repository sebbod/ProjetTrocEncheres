package fr.eni.bids.dal;

import fr.eni.bids.BidsException;
import fr.eni.bids.bo.Bid;
import fr.eni.bids.bo.Category;
import fr.eni.bids.bo.Item;
import fr.eni.bids.bo.PickUpAdress;
import fr.eni.bids.bo.User;
import fr.eni.bids.dal.jdbc.BidJDBCDAOImpl;
import fr.eni.bids.dal.jdbc.CategoryJDBCDAOImpl;
import fr.eni.bids.dal.jdbc.ItemJDBCDAOImpl;
import fr.eni.bids.dal.jdbc.PickUpAdressJDBCDAOImpl;
import fr.eni.bids.dal.jdbc.UserJDBCDAOImpl;

public class DAOFactory {

	public static DAO<Item> getItemDAO() throws BidsException {
		return new ItemJDBCDAOImpl();
	}

	public static DAO<User> getUserDAO() throws BidsException {
		return new UserJDBCDAOImpl();
	}

	public static DAO<Category> getCategoryDAO() throws BidsException {
		return new CategoryJDBCDAOImpl();
	}

	public static DAO<PickUpAdress> getPickUpAdressDAO() throws BidsException {
		return new PickUpAdressJDBCDAOImpl();
	}

	public static DAO<Bid> getBidDAO() throws BidsException {
		return new BidJDBCDAOImpl();
	}

	public static DAO<?> getBusinessObjectDAO(String classSimpleName) throws BidsException {
		System.out.println("classSimpleName : " + classSimpleName);
		switch (classSimpleName) {
		case "Item":
			return getItemDAO();
		case "User":
			return getUserDAO();
		case "Category":
			return getCategoryDAO();
		case "PickUpAdress":
			return getPickUpAdressDAO();
		case "Bid":
			return getBidDAO();
		default:
			throw new BidsException(ErrorCodesDAL.GENERIC_FACTORY_ERROR);
		}
	}

	public static LoginDAO getLoginDAO() {
		LoginDAO loginDAO = null;
		try {
			loginDAO = (LoginDAO) Class.forName("fr.eni.bids.dal.jdbc.LoginDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return loginDAO;
	}

}
