package fr.eni.bids.dal.jdbc;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import fr.eni.bids.BidsException;
import fr.eni.bids.bo.Item;

public class ItemJDBCDAOImpl extends GenericJDBCDAOImpl<Item> {
	public ItemJDBCDAOImpl() throws BidsException {
		super();
	}

	@Override
	protected void setIdentifiers() {
		this.identifiers = new ArrayList<>();
		this.identifiers.add("id");
		this.autogeneratedIdentifiers = 1;
	}

	@Override
	protected void setTableName() {
		this.tableName = "BID_ITEMS";
	}

	@SuppressWarnings("serial")
	@Override
	protected void setFields() {
		this.fields = new LinkedHashMap<String, String>() {
			{
				put("id", "Integer");
				put("name", "String");
				put("description", "String");
				put("dateStart", "LocalDateTime");
				put("dateEnd", "LocalDateTime");
				put("priceSeller", "Int");
				put("priceBuyer", "Int");
				put("userIdSeller", "fr.eni.bids.dal.jdbc.UserJDBCDAOImpl");
				put("userIdBuyer", "fr.eni.bids.dal.jdbc.UserJDBCDAOImpl");
				put("cateId", "fr.eni.bids.dal.jdbc.CategoryJDBCDAOImpl");
				put("status", "String");
			}
		};
	}

	protected Item getObject() {
		return new Item();
	}
}
