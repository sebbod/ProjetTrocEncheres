package fr.eni.bids.dal.jdbc;

import java.util.HashMap;
import java.util.Map;

public abstract class ErrorCodesJDBC {
	public static final int DATABASE_ACCESS_ERROR = 10100;
	public static final int CONNECTION_ERROR = 10110;
	public static final int CRUD_INSERT_OR_UPDATE_ERROR = 10200;
	public static final Map<String, Integer> GENERATE_OBJECT_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 10201);
			put("Category", 10202);
			put("Bid", 10203);
			put("PickUpAdress", 10204);
			put("User", 10205);
		}
	};
	public static final Map<String, Integer> GENERATE_STATEMENT_DATA_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 10221);
			put("Category", 10222);
			put("Bid", 10223);
			put("PickUpAdress", 10224);
			put("User", 10225);
		}
	};
	public static final Map<String, Integer> CRUD_INSERT = new HashMap<String, Integer>() {
		{
			put("Item", 10301);
			put("Category", 10302);
			put("Bid", 10303);
			put("PickUpAdress", 10304);
			put("User", 10305);
		}
	};
	public static final Map<String, Integer> CRUD_UPDATE = new HashMap<String, Integer>() {
		{
			put("Item", 10401);
			put("Category", 10402);
			put("Bid", 10403);
			put("PickUpAdress", 10404);
			put("User", 10405);
		}
	};
	public static final Map<String, Integer> CRUD_DELETE_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 10501);
			put("Category", 10502);
			put("Bid", 10503);
			put("PickUpAdress", 10504);
			put("User", 10505);
		}
	};
	public static final int CRUD_SELECT_BY_ERROR = 10600;
	public static final Map<String, Integer> CRUD_SELECT_ID_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 10601);
			put("Category", 10602);
			put("Bid", 10603);
			put("PickUpAdress", 10604);
			put("User", 10605);
		}
	};
	public static final Map<String, Integer> CRUD_SELECT_FIELD_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 10611);
			put("Category", 10612);
			put("Bid", 10613);
			put("PickUpAdress", 10614);
			put("User", 10615);
		}
	};
	public static final int CRUD_SELECT_ALL_BY_ERROR = 10620;
	public static final Map<String, Integer> CRUD_SELECT_ALL_ERROR = new HashMap<String, Integer>() {
		{
			put("Item", 10621);
			put("Category", 10622);
			put("Bid", 10623);
			put("PickUpAdress", 10624);
			put("User", 10625);
		}
	};

}
