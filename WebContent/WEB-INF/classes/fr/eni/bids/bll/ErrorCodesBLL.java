package fr.eni.bids.bll;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodesBLL {
	// Error during the initialization using DAOFactory.
	public static final Map<String, Integer> INITIALIZATION_DAO_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 20001);
			put("Category", 20002);
			put("Bid", 20003);
			put("PickUpAdress", 20004);
			put("User", 20005);
		}
	};
	// Business object is null.
	public static final Map<String, Integer> BO_NULL_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 20201);
			put("Category", 20202);
			put("Bid", 20203);
			put("PickUpAdress", 20204);
			put("User", 20205);
		}
	};
	// Error while attempting to insert a business object to the database.
	public static final Map<String, Integer> ADD_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 20301);
			put("Category", 20302);
			put("Bid", 20303);
			put("PickUpAdress", 20304);
			put("User", 20305);
		}
	};
	// Business object already exist in the database.
	public static final Map<String, Integer> ADD_ALREADY_EXIST_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 20311);
			put("Category", 20312);
			put("Bid", 20313);
			put("PickUpAdress", 20314);
			put("User", 20315);
		}
	};
	// Error while attempting to update a business object to the database.
	public static final Map<String, Integer> UPDATE_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 20401);
			put("Category", 20402);
			put("Bid", 20403);
			put("PickUpAdress", 20404);
			put("User", 20405);
		}
	};
	// Business object doesn't exist in the database.
	public static final Map<String, Integer> UPDATE_NOT_EXIST_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 20411);
			put("Category", 20412);
			put("Bid", 20413);
			put("PickUpAdress", 20414);
			put("User", 20415);
		}
	};
	// Error while attempting to delete a business object to the database.
	public static final Map<String, Integer> DELETE_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 20501);
			put("Category", 20502);
			put("Bid", 20503);
			put("PickUpAdress", 20504);
			put("User", 20505);
		}
	};
	// Business object doesn't exist in the database.
	public static final Map<String, Integer> DELETE_NOT_EXIST_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 20511);
			put("Category", 20512);
			put("Bid", 20513);
			put("PickUpAdress", 20514);
			put("User", 20515);
		}
	};
	// Error while retrieving a business object by ID.
	public static final Map<String, Integer> GET_BY_ID_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 20601);
			put("Category", 20602);
			put("Bid", 20603);
			put("PickUpAdress", 20604);
			put("User", 20605);
		}
	};
	// Error while retrieving a business object by field.
	public static final Map<String, Integer> GET_BY_FIELD_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 20611);
			put("Category", 20612);
			put("Bid", 20613);
			put("PickUpAdress", 20614);
			put("User", 20615);
		}
	};
	// Error while retrieving a list of business objects by ID.
	public static final Map<String, Integer> GET_ALL_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 20621);
			put("Category", 20622);
			put("Bid", 20623);
			put("PickUpAdress", 20624);
			put("User", 20625);
		}
	};
	// Error specific to the ItemManager.
	public static final int ITEM_SET_ITEM_WON = 21421;
	public static final int ITEM_GET_ALL_FROM_ERROR = 21601;
	public static final int ITEM_GET_ALL_BY_NAME_DESCRIPTION = 21611;
	// Error specific to the BidManager.
	public static final int BID_DELETE_ALL_BY = 23503;
	public static final int BID_GET_BIDS_FROM = 23603;
	public static final int BID_GET_HIGHEST_BID = 23613;
	// Error specific to the UserManager.
	public static final int AUTHENTICATION_ERROR = 25605;
	public static final int USER_GET_BY_PSEUDO_ERROR = 25615;
	public static final int USER_GET_BY_EMAIL_ERROR = 25625;

}
