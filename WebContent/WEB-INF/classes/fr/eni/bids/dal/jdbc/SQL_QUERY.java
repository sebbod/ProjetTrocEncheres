package fr.eni.bids.dal.jdbc;

public class SQL_QUERY {

	public static final String USER_GET_BY_ID = "SELECT [id]\r\n" + "      ,[pseudo]\r\n" + "      ,[name]\r\n"
			+ "      ,[firstName]\r\n" + "      ,[email]\r\n" + "      ,[telephone]\r\n" + "      ,[street]\r\n"
			+ "      ,[zipCode]\r\n" + "      ,[town]\r\n" + "      ,[pwd]\r\n" + "      ,[credit]\r\n"
			+ "      ,[isAdmin]\r\n" + "  FROM [dbo].[USERS]\r\n" + "  WHERE [id] =?;";

	public static final String USER_ALL = "SELECT [id]\r\n" + "      ,[pseudo]\r\n" + "      ,[name]\r\n"
			+ "      ,[firstName]\r\n" + "      ,[email]\r\n" + "      ,[telephone]\r\n" + "      ,[street]\r\n"
			+ "      ,[zipCode]\r\n" + "      ,[town]\r\n" + "      ,[pwd]\r\n" + "      ,[credit]\r\n"
			+ "      ,[isAdmin]\r\n" + "  FROM [dbo].[USERS];";

	public static final String USER_INSERT = "INSERT INTO [dbo].[USERS]\r\n" + "           ([pseudo]\r\n"
			+ "           ,[name]\r\n" + "           ,[firstName]\r\n" + "           ,[email]\r\n"
			+ "           ,[telephone]\r\n" + "           ,[street]\r\n" + "           ,[zipCode]\r\n"
			+ "           ,[town]\r\n" + "           ,[pwd]\r\n" + "           ,[credit]\r\n"
			+ "           ,[isAdmin])\r\n" + "     VALUES\r\n" + "           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	public static final String USER_UPDATE = "UPDATE [dbo].[USERS]\r\n" + "   SET [pseudo] = ?\r\n"
			+ "      ,[name] = ?\r\n" + "      ,[firstName] = ?\r\n" + "      ,[email] = ?\r\n"
			+ "      ,[telephone] = ?\r\n" + "      ,[street] = ?\r\n" + "      ,[zipCode] = ?\r\n"
			+ "      ,[town] = ?\r\n" + "      ,[pwd] = ?\r\n" + "      ,[credit] = ?\r\n" + "      ,[isAdmin] = ?\r\n"
			+ " WHERE [id] = ?";

}
