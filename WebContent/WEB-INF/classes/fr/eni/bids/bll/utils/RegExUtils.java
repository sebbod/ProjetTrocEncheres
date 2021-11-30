package fr.eni.bids.bll.utils;

public class RegExUtils {
	public static boolean isAlphaNumeric(String s) {
		return s != null && s.matches("^[a-zA-Z0-9]*$");
	}

}
