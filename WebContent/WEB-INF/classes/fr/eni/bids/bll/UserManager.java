package fr.eni.bids.bll;

import fr.eni.bids.BidsException;
import fr.eni.bids.bll.utils.RegExUtils;
import fr.eni.bids.bll.utils.TrippleDes;
import fr.eni.bids.bo.User;
import fr.eni.bids.dal.DAO;
import fr.eni.bids.dal.DAOFactory;

public class UserManager extends GenericManager<User> {
	private static DAO<User> userDao;

	public UserManager() throws BidsException {
		super();
		userDao = DAOFactory.getUserDAO();
	}

	// CRUD

	public User getByPseudo(String pseudo) throws BidsException {
		try {
			return this.userDao.selectByField("pseudo", pseudo);
		} catch (BidsException BidsException) {
			BidsException.printStackTrace();
			throw new BidsException(ErrorCodesBLL.USER_GET_BY_PSEUDO_ERROR, BidsException);
		}
	}

	public User getByEmail(String email) throws BidsException {
		try {
			return userDao.selectByField("email", email);
		} catch (BidsException BidsException) {
			BidsException.printStackTrace();
			throw new BidsException(ErrorCodesBLL.USER_GET_BY_EMAIL_ERROR, BidsException);
		}
	}

	public User getByPseudoAndPassword(String pseudo, String password) throws BidsException {
		User u = getByPseudo(pseudo);

		TrippleDes crypto;
		String hPwd = "!bad_pwd!";
		try {
			crypto = new TrippleDes();
			hPwd = crypto.encrypt(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println("getByPseudoAndPassword : User.getPwd()=" + u.getPwd() + ", password=" + password + ",hPwd=" + hPwd);
		if (u != null && u.getPwd().equals(hPwd)) {
			return u;
		} else {
			throw new BidsException(ErrorCodesBLL.AUTHENTICATION_ERROR);
		}
	}

	@Override
	public User add(User User) throws BidsException {
		return super.add(User);
	}

	@Override
	public User update(User User) throws BidsException {
		return super.update(User);
	}

	// LOGIC & CHECKS

	@Override
	protected int[] getIdentifiers(User u) {
		return new int[] { u.getId() };
	}

	@Override
	protected void executeUpdate(User u, String operationCRUD) throws BidsException {
		if (operationCRUD.equals("DELETE")) {
			new BidManager().deleteAllBy(u);
			new ItemManager().deleteAllBySeller(u);
		}
	}

	/**
	 * Check all the attributes of an User.
	 * 
	 * @param User
	 *            User | User to check.
	 * @throws BidsException
	 *             BidsException | Newly created exception.
	 */
	protected void checkAttributes(User User) throws BidsException {
		if (User == null) {
			throw new BidsException(ErrorCodesBLL.BO_NULL_ERROR.get("User"));
		}
		StringBuilder errors = new StringBuilder();
		if (User.getPseudo() == null || User.getPseudo().isEmpty()) {
			errors.append("Champs obligatoire. L'utilisateur n'a pas de pseudonyme.").append("\n");
		}
		if (!RegExUtils.isAlphaNumeric(User.getPseudo())) {
			errors.append("Le pseudo ne doit contenir que des lettres et des chiffres.").append("\n");
		}

		if (User.getName() == null || User.getName().isEmpty()) {
			errors.append("Champs obligatoire. L'utilisateur n'a pas de nom.").append("\n");
		}
		if (User.getFirstName() == null || User.getFirstName().isEmpty()) {
			errors.append("Champs obligatoire. L'utilisateur n'a pas de nom.").append("\n");
		}
		if (User.getEmail() == null || User.getEmail().isEmpty()) {
			errors.append("Champs obligatoire. L'utilisateur n'a pas d'e-mail.").append("\n");
		}
		if (User.getStreet() == null || User.getStreet().isEmpty()) {
			errors.append("Champs obligatoire. L'utilisateur n'a pas de rue renseignée pour son adresse.").append("\n");
		}
		if (User.getZipCode() == null || User.getZipCode().isEmpty()) {
			errors.append("Champs obligatoire. L'utilisateur n'a pas de code postal renseigné pour son adresse.").append("\n");
		}
		if (User.getTown() == null || User.getTown().isEmpty()) {
			errors.append("Champs obligatoire. L'utilisateur n'a pas de ville renseignée pour son adresse.").append("\n");
		}
		if (User.getPwd() == null || User.getPwd().isEmpty()) {
			errors.append("Champs obligatoire. L'utilisateur n'a pas de mot de passe.").append("\n");
		}
		if (User.getCredit() < 0) {
			errors.append("Champs incorrecte. Le nombre de crédits ne peut pas être négatif").append("\n");
		}
		if (!errors.toString().isEmpty()) {
			throw new BidsException(errors.toString());
		}
	}

	protected boolean exist(User User) throws BidsException {
		System.out.println("checkUnity, User=" + User);

		User uWithSameMail = getByEmail(User.getEmail());
		if (uWithSameMail != null && uWithSameMail.getId() != User.getId()) {
			return false;
		}

		User uWithSamePseudo = getByPseudo(User.getPseudo());
		if (uWithSamePseudo != null && uWithSamePseudo.getId() != User.getId()) {
			return false;
		}

		return getByEmail(User.getEmail()) != null && getByPseudo(User.getPseudo()) != null;
	}
}
