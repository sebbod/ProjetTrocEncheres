package fr.eni.bids.bll;

import java.util.ArrayList;
import java.util.List;

public class BLLException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8571287878343204203L;
	private List<Integer> lstErrorCode;

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

	@Override
	public String getMessage() {

		return "BLL - " + super.getMessage();
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
