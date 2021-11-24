package fr.eni.bids.rest;

public class ErrorCodes {

	public static final int NAME_ERROR = 20000;
	public static final int PSEUDO_ERROR = 20001;
	public static final int FIRSTNAME_ERROR = 20002;
	public static final int EMAIL_ERROR = 20003;
	public static final int TELEPHONE_ERROR = 20004;
	public static final int STREET_ERROR = 20005;
	public static final int ZIPCODE_ERROR = 20006;
	public static final int TOWN_ERROR = 20007;
	public static final int PWD_ERROR = 20008;

	public static final int USER_EXISTS = 20009;
	public static final int EMAIL_EXISTS = 20010;
	public static final int SALE_END_DATE = 20011;

	public static final int MY_OFFER_SUP_CURRENT_OFFER = 20012;
	public static final int MY_OFFER_SUP_STARTING_BID = 20013;
	public static final int MY_OFFER_INF_MY_POINTS = 20014;

	public static final int USER_UPDATE_FORBIDDEN = 20015;

	static public int SESSION_VALIDATION_ERROR = 30000;
}
