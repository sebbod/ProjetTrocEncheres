package fr.eni.bids.bll.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtils {

	public static boolean isAlphaNumeric(String s) {
		return s != null && s.matches("^[a-zA-Z0-9]*$");
	}

	public static boolean isMail(String s) {
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(s);
		return s != null && m.find();
	}

}
