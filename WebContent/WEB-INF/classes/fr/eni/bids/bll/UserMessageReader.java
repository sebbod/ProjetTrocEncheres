package fr.eni.bids.bll;

import java.util.ResourceBundle;

/**
 * read file in user_messages_fr.properties or user_messages_en
 */
public abstract class UserMessageReader {
	private static ResourceBundle rb;

	static {
		try {
			rb = ResourceBundle.getBundle("fr.eni.bids.bll.user_messages_fr");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param code
	 * @return
	 */
	public static String getById(int id) {
		String message = "";
		try {
			if (rb != null) {
				message = rb.getString(String.valueOf(id));
			} else {
				message = "Problème à la lecture du fichier contenant les messages";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "Une erreur inconnue est survenue";
		}
		return message;
	}
}