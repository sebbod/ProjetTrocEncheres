package fr.eni.bids.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.bids.msg.MessageReader;

public class BLLException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8571287878343204203L;
	private List<Integer> lstErrorCode;
	private int code;

	public BLLException() {
		super();
		this.lstErrorCode = new ArrayList<>();
	}

	public BLLException(String message) {
		super(message);
	}

	public BLLException(String message, Throwable exception) {
		super(message, exception);
	}

	public BLLException(String message, Exception exception) {
		super(message, exception);
	}

	public BLLException(int code, Exception exception) {
		super(decode(code), exception);
		setCode(code);
	}

	/**
	 * Get an error message from its code.
	 * 
	 * @param code
	 *            int | Error code.
	 * @return String | Error message.
	 */
	private static String decode(int code) {
		return MessageReader.getById(code);
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return "Bids | " + super.getMessage();
	}

	@Override
	public void printStackTrace() {
		System.out.println(getMessage());
		super.printStackTrace();
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
