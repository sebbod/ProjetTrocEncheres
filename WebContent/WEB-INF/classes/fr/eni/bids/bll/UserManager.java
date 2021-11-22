package fr.eni.bids.bll;

import java.util.List;

import fr.eni.bids.bo.User;
import fr.eni.bids.dal.DALException;
import fr.eni.bids.dal.DAOFactory;
import fr.eni.bids.dal.UserDAO;

public class UserManager {
	private static UserDAO userDao;

	public UserManager() throws BLLException {
		userDao = DAOFactory.getUserDAO();
	}

	public User getById(int id) throws BLLException {
		User u = null;
		try {
			u = userDao.getById(id);
		} catch (DALException e) {
			throw new BLLException("getById()", e);
		}

		return u;
	}

	public List<User> getAll() throws BLLException {
		List<User> lst = null;
		try {
			lst = userDao.getAll();
		} catch (DALException e) {
			throw new BLLException("getAll()", e);
		}

		return lst;
	}

	// method in order to create a new user in database
	public void insert(String pseudo, String name, String firstName, String email, String telephone, String street,
			String zipCode, String town, String pwd) throws BLLException {
		User u = new User(pseudo, name, firstName, email, telephone, street, zipCode, town, pwd, 0, false);
		try {
			this.validate4Insert(u);
			userDao.insert(u);
		} catch (DALException e) {
			throw new BLLException("insert(...)" + u, e);
		}
	}

	// method to update user infos
	public void update(int id, String pseudo, String name, String firstName, String email, String telephone,
			String street, String zipCode, String town, String pwd) throws BLLException {
		User u = new User(id, pseudo, name, firstName, email, telephone, street, zipCode, town, pwd, 0, false);
		try {
			this.validate4Update(u);
			userDao.update(u);
		} catch (DALException e) {
			throw new BLLException("update(...)" + u, e);
		}
	}

	// User validation before insertion in database
	private void validate4Update(User u) throws BLLException {
		BLLException be = new BLLException();

		validateCommon(u, be);

		if (be.hasError()) {
			throw new BLLException(be.getLstErrorCode().toString());
		}
	}

	// User validation before insertion in database
	private void validate4Insert(User u) throws BLLException {
		BLLException be = new BLLException();

		validateCommon(u, be);

		if (be.hasError()) {
			throw new BLLException(be.getLstErrorCode().toString());
		}
	}

	// Common validation for insert and update
	private void validateCommon(User u, BLLException be) throws BLLException {
		if (u == null) {
			throw new BLLException("validateCommon() : User is null");
		}

		if (u.getPseudo() == null || u.getPseudo().equals("") || u.getPseudo().length() > 30) {
			be.add(UserErrorCode.PSEUDO_ERROR);
		}
		if (u.getName() == null || u.getName().equals("") || u.getName().length() > 30) {
			be.add(UserErrorCode.NAME_ERROR);
		}
		if (u.getFirstName() == null || u.getFirstName().equals("") || u.getFirstName().length() > 30) {
			be.add(UserErrorCode.FIRSTNAME_ERROR);
		}
		if (u.getEmail() == null || u.getEmail().equals("") || u.getEmail().length() > 40
				|| !u.getEmail().contains("@")) {
			be.add(UserErrorCode.EMAIL_ERROR);
		}
		if (u.getTelephone() == null || u.getTelephone().equals("") || u.getTelephone().length() > 15
				|| u.getTelephone().contains("[a-zA-Z]")) {
			be.add(UserErrorCode.TELEPHONE_ERROR);
		}
		if (u.getStreet() == null || u.getStreet().equals("") || u.getStreet().length() > 30) {
			be.add(UserErrorCode.STREET_ERROR);
		}
		if (u.getZipCode() == null || u.getZipCode().equals("") || u.getZipCode().length() > 10
				|| u.getZipCode().contains("[a-zA-Z]")) {
			be.add(UserErrorCode.ZIPCODE_ERROR);
		}
		if (u.getTown() == null || u.getTown().equals("") || u.getTown().length() > 40) {
			be.add(UserErrorCode.TOWN_ERROR);
		}
		if (u.getPwd() == null || u.getPwd().equals("") || u.getPwd().length() > 50) {
			be.add(UserErrorCode.PWD_ERROR);
		}
	}

}
