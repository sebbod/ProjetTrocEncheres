package fr.eni.bids.bll;

import fr.eni.bids.dal.DALException;
import fr.eni.bids.dal.DAOFactory;
import fr.eni.bids.dal.LoginDAO;

public class LoginManager {
	private static LoginDAO loginDAO;

	public LoginManager() throws BLLException {
		loginDAO = DAOFactory.getLoginDAO();
	}

	/* If validate pseudo/password, return user id */
	public int validate(String pseudo, String password) throws BLLException {
		int id = 0;
		try {
			id = loginDAO.validate(pseudo, password);
		} catch (DALException e) {
			throw new BLLException("validate()", e);
		}
		return id;
	}

}
