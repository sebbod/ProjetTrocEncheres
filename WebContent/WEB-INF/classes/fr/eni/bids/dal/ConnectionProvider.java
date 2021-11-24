package fr.eni.bids.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fr.eni.bids.BidsException;
import fr.eni.bids.dal.jdbc.ErrorCodesJDBC;

public abstract class ConnectionProvider {

	private static DataSource dataSource;

	/**
	 * Au chargement de la classe, la DataSource est recherchée dans l'arbre JNDI
	 */
	static {
		Context context;
		try {
			context = new InitialContext();
			ConnectionProvider.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/bids_cnx");
		} catch (NamingException e) {
			try {
				throw new BidsException(ErrorCodesJDBC.DATABASE_ACCESS_ERROR, e);
			} catch (BidsException bException) {
				throw new RuntimeException();
			}
		}
	}

	/**
	 * Cette méthode retourne une connection opérationnelle issue du pool de
	 * connexion vers la base de données.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws BidsException {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new BidsException(ErrorCodesJDBC.CONNECTION_ERROR, e);
		}
	}
}