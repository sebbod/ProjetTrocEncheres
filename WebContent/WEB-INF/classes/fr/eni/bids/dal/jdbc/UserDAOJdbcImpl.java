/*
 * 
 * 
 */
/*
 * package fr.eni.bids.dal.jdbc;
 * 
 * import java.sql.Connection; import java.sql.PreparedStatement; import
 * java.sql.ResultSet; import java.sql.SQLException; import java.util.ArrayList;
 * import java.util.List;
 * 
 * import fr.eni.bids.BidsException; import fr.eni.bids.bo.User; import
 * fr.eni.bids.dal.ConnectionProvider;
 * 
 * public class UserDAOJdbcImpl implements UserDAO {
 * 
 * 
 * @Override public User getById(Integer id) throws BidsException { User user =
 * new User(); PreparedStatement prepStmt = null; ResultSet rs = null; try
 * (Connection cnx = ConnectionProvider.getConnection()) { prepStmt =
 * cnx.prepareStatement(SQL_QUERY.USER_GET_BY_ID); prepStmt.setInt(1, id); rs =
 * prepStmt.executeQuery(); if (rs.next()) { user.setId(rs.getInt("id"));
 * user.setPseudo(rs.getString("pseudo")); user.setPwd(rs.getString("pwd"));
 * user.setName(rs.getString("name"));
 * user.setFirstName(rs.getString("firstName"));
 * user.setEmail(rs.getString("email"));
 * user.setTelephone(rs.getString("telephone"));
 * user.setStreet(rs.getString("street")); user.setTown(rs.getString("town"));
 * user.setZipCode(rs.getString("zipCode"));
 * user.setCredit(rs.getInt("credit"));
 * user.setIsAdmin(rs.getBoolean("isAdmin")); } } catch (BidsException e) {
 * e.printStackTrace(); throw new BidsException(e.getMessage()); } finally { if
 * (prepStmt != null) { try { prepStmt.close(); } catch (SQLException e) {
 * e.printStackTrace(); } } if (rs != null) { try { rs.close(); } catch
 * (SQLException e) { e.printStackTrace(); } } } return user; }
 * 
 * 
 * @Override public List<User> getAll() throws BidsException { List<User> uLst =
 * new ArrayList<>(); PreparedStatement ps = null; try (Connection cnx =
 * ConnectionProvider.getConnection()) { ps =
 * cnx.prepareStatement(SQL_QUERY.USER_ALL); ResultSet rs = ps.executeQuery();
 * while (rs.next()) { User user = new User(); user.setId(rs.getInt("id"));
 * user.setPseudo(rs.getString("pseudo")); user.setPwd(rs.getString("pwd"));
 * user.setName(rs.getString("name"));
 * user.setFirstName(rs.getString("firstName"));
 * user.setEmail(rs.getString("email"));
 * user.setTelephone(rs.getString("telephone"));
 * user.setStreet(rs.getString("street")); user.setTown(rs.getString("town"));
 * user.setZipCode(rs.getString("zipCode"));
 * user.setCredit(rs.getInt("credit"));
 * user.setIsAdmin(rs.getBoolean("isAdmin")); uLst.add(user); } } catch
 * (BidsException e) { e.printStackTrace(); throw new
 * BidsException(e.getMessage()); } // System.out.println(articlesSelected);
 * finally { if (ps != null) { try { ps.close(); } catch (SQLException e) {
 * e.printStackTrace(); } } } return uLst; }
 * 
 * 
 * @Override public void insert(User u) throws BidsException { PreparedStatement
 * ps = null; try (Connection cnx = ConnectionProvider.getConnection()) { ps =
 * cnx.prepareStatement(SQL_QUERY.USER_INSERT); ps.setString(1, u.getPseudo());
 * ps.setString(2, u.getName()); ps.setString(3, u.getFirstName());
 * ps.setString(4, u.getEmail()); ps.setString(5, u.getTelephone());
 * ps.setString(6, u.getStreet()); ps.setString(7, u.getZipCode());
 * ps.setString(8, u.getTown()); ps.setString(9, u.getPwd()); ps.setInt(10,
 * u.getCredit()); ps.setBoolean(11, u.getIsAdmin()); ps.executeUpdate(); }
 * catch (SQLException e) { e.printStackTrace(); throw new
 * BidsException(e.getMessage()); } finally { if (ps != null) { try {
 * ps.close(); } catch (SQLException e) { e.printStackTrace(); } } } }
 * 
 * 
 * @Override public void update(User u) throws BidsException { PreparedStatement
 * ps = null; try (Connection cnx = ConnectionProvider.getConnection()) { ps =
 * cnx.prepareStatement(SQL_QUERY.USER_UPDATE); ps.setString(1, u.getPseudo());
 * ps.setString(2, u.getName()); ps.setString(3, u.getFirstName());
 * ps.setString(4, u.getEmail()); ps.setString(5, u.getTelephone());
 * ps.setString(6, u.getStreet()); ps.setString(7, u.getZipCode());
 * ps.setString(8, u.getTown()); ps.setString(9, u.getPwd()); ps.setInt(10,
 * u.getCredit()); ps.setBoolean(11, u.getIsAdmin()); ps.setInt(12, u.getId());
 * ps.executeUpdate(); } catch (SQLException e) { e.printStackTrace(); throw new
 * BidsException(e.getMessage()); } finally { if (ps != null) { try {
 * ps.close(); } catch (SQLException e) { e.printStackTrace(); } } } }
 * 
 * 
 * @Override public void delete(Integer id) throws BidsException {
 * PreparedStatement ps = null; try (Connection cnx =
 * ConnectionProvider.getConnection()) { ps =
 * cnx.prepareStatement(SQL_QUERY.USER_DELETE); ps.setInt(1, id);
 * ps.executeUpdate(); } catch (SQLException e) { e.printStackTrace(); throw new
 * BidsException(e.getMessage()); } finally { if (ps != null) { try {
 * ps.close(); } catch (SQLException e) { e.printStackTrace(); } } } }
 * 
 * }
 */