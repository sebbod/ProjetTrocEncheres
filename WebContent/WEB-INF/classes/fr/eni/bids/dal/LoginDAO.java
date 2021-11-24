package fr.eni.bids.dal;

public interface LoginDAO {
	/* Validate pseudo and password to login */
	public int validate(String pseudo, String password) throws DALException;
}
