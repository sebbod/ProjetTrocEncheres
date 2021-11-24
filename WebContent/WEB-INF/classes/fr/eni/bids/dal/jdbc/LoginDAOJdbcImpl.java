package fr.eni.bids.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.bids.BidsException;
import fr.eni.bids.bo.User;
import fr.eni.bids.dal.ConnectionProvider;
import fr.eni.bids.dal.DALException;
import fr.eni.bids.dal.LoginDAO;

public class LoginDAOJdbcImpl implements LoginDAO {

	@Override
	public int validate(String pseudo, String password) throws DALException {
		int id = 0;

		User user = new User();
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			prepStmt = cnx.prepareStatement(SQL_QUERY.USER_GET_BY_PSEUDO);
			prepStmt.setString(1, pseudo);
			rs = prepStmt.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setPseudo(rs.getString("pseudo"));
				user.setPwd(rs.getString("pwd"));
			}
		} catch (SQLException | BidsException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		} finally {
			if (prepStmt != null) {
				try {
					prepStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Pseudo de l'utilisateur" + user.getPseudo() + " - Password " + user.getPwd());

		// CHECK PASSWORD
		if (password.equals(user.getPwd())) {
			id = user.getId();
		} else {
			// THROW LOGIN ERROR
		}
		return id;
	}

}
