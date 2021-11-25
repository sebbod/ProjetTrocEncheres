package fr.eni.bids;

import java.util.List;

import fr.eni.bids.msg.PropertiesReader;

public class BidsException extends Exception {
	private static final long serialVersionUID = 1L;
	private List<Integer> lstErrorCode;
	private int code;

	// CONSTRUCTORS

	public BidsException() {
		super();
	}

	public BidsException(String message) {
		super(message);
	}

	public BidsException(String message, Exception exception) {
		super(message, exception);
	}

	public BidsException(int code) {
		super(decode(code));
		setCode(code);
	}

	public BidsException(int code, Exception exception) {
		super(decode(code), exception);
		setCode(code);
	}

	// METHODS

	/**
	 * Get an error message from its code.
	 * 
	 * @param code
	 *            int | Error code.
	 * @return String | Error message.
	 */
	private static String decode(int code) {
		return PropertiesReader.getProperty(code);
	}

	@Override
	public String getMessage() {
		return "Enchères | " + super.getMessage();
	}

	@Override
	public void printStackTrace() {
		System.out.println(getMessage());
		super.printStackTrace();
	}

	// GETTERS & SETTERS

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

	/**
	 * 
	 * @param code
	 *            Must be associate with message in properties file
	 */
	public void add(int code) {
		if (!this.lstErrorCode.contains(code)) {
			this.lstErrorCode.add(code);
		}
	}

	public boolean hasError() {
		return this.lstErrorCode.size() > 0;
	}

	public List<Integer> getLstErrorCode() {
		return this.lstErrorCode;
	}
}
