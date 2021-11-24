package fr.eni.bids.msg;

import java.util.Properties;

public class PropertiesReader {
	private static Properties properties;
	static {
		try {
			properties = new Properties();
			properties.load(PropertiesReader.class.getResourceAsStream("errors_messages_fr.properties"));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static String getProperty(int key) {
		return properties.getProperty(String.valueOf(key), null);
	}
}