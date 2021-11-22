package fr.eni.bids.dal;

public class DAOFactory {

	public static UserDAO getArticleDAO() {
		UserDAO userDAO = null;
		try {
			userDAO = (UserDAO) Class.forName("fr.eni.dal.jdbc.ArticleDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userDAO;
	}

}
