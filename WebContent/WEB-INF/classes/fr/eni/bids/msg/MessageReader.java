package fr.eni.bids.msg;

import java.util.ResourceBundle;

public abstract class MessageReader {
	private static ResourceBundle rb;
	private static final String FILENAME = "fr.eni.bids.errors_messages_fr";

	static {
		try {
			rb = ResourceBundle.getBundle(FILENAME);
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
