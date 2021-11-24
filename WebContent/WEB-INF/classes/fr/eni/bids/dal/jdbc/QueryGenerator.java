package fr.eni.bids.dal.jdbc;

import fr.eni.bids.BidsException;

public class QueryGenerator {
	// GENERIC

	public static String INSERT(String table, int numberOfParameters) {
		StringBuilder unknownParameters = new StringBuilder();
		String separator = "";
		while (numberOfParameters > 0) {
			unknownParameters.append(separator).append("?");
			separator = ", ";
			numberOfParameters--;
		}
		return "INSERT INTO " + table + " VALUES ( " + unknownParameters.toString() + " ) ";
	}

	public static String UPDATE(String table, String fields, String conditions) {
		return "UPDATE " + table + " SET " + fields + " WHERE " + conditions;
	}

	public static String DELETE(String table, String conditions) {
		return "DELETE FROM " + table + " WHERE " + conditions;
	}

	public static String SELECT(String fields, String table, String conditions) {
		return "SELECT " + fields + " FROM " + table + " WHERE " + conditions;
	}

	public static String SELECT_ALL(String fields, String table) {
		return "SELECT " + fields + " FROM " + table;
	}

	// ARTICLE
	public static String SELECT_ITEM_LIKE(String variable, String category) throws BidsException {
		return "SELECT " + new ItemJDBCDAOImpl().generateQueryFields() + " " + "FROM Item " + "WHERE (name LIKE '%"
				+ variable + "%' OR description LIKE '%" + variable + "%') "
				+ (category == null ? "" : "AND category = " + category + " ") + "ORDER BY name";
	}

	// ENCHERE

	public static String SELECT_BID_MAX() throws BidsException {
		return "SELECT " + new BidJDBCDAOImpl().generateQueryFields() + " " + "FROM Enchere " + "INNER JOIN "
				+ "(SELECT articleVendu AS articleVenduMax, MAX(montantEnchere) AS montantEnchereMax " + "FROM Enchere "
				+ "WHERE articleVendu = ? " + "GROUP BY articleVendu) " + "AS EnchereMax "
				+ "ON Enchere.articleVendu = EnchereMax.articleVenduMax AND Enchere.montantEnchere = EnchereMax.montantEnchereMax";
	}
}
