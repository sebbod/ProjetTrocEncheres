package fr.eni.bids.dal.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.eni.bids.BidsException;
import fr.eni.bids.dal.ConnectionProvider;
import fr.eni.bids.dal.DAO;

public abstract class GenericJDBCDAOImpl<T> implements DAO<T> {
	public List<String> identifiers;
	protected int autogeneratedIdentifiers;
	protected String tableName;
	protected Map<String, String> fields;
	protected Class<T> entityClass;

	// CONSTRUCTOR

	@SuppressWarnings("unchecked")
	protected GenericJDBCDAOImpl() throws BidsException {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		setIdentifiers();
		setTableName();
		setFields();
	}

	// ABSTRACT METHODS

	/**
	 * The "identifiers" are the field use for the selectById() methods. The "autogeneratedIdentifiers" attribute is the number of auto-generated identifiers.
	 */
	protected abstract void setIdentifiers();

	/*
	 * SQL table name
	 */
	protected abstract void setTableName();

	/**
	 * The "fields" attribute must be a LinkedHashMap with the names of the fields as keys, and the data type as attribute. If the data type is a Business Object, the value must be the path to its DAO's implementation.
	 */
	protected abstract void setFields();

	protected abstract T getObject();

	// CRUD METHODS

	// INSERT OR UPDATE
	/**
	 * @param object
	 *            T | Instance of the actual object to insert or update into the database.
	 * @param query
	 *            String | SQL Query.
	 * @param isUpdate
	 *            boolean | "true" if the operation is an update, "false" if it is an insert.
	 * @return T | Instance of the actual object successfully inserted or updated into the database.
	 * @throws BidsException
	 */
	private T insertOrUpdate(T object, String query, boolean isUpdate) throws BidsException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			setStatementParameters(statement, object, isUpdate);
			statement.executeUpdate();
			if (!isUpdate) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (this.autogeneratedIdentifiers > 0 && resultSet.next()) {
					String identifier = this.identifiers.get(0);
					String method = "set" + identifier.substring(0, 1).toUpperCase() + identifier.substring(1);
					switch (this.fields.get(identifier)) {
					case "Integer":
						entityClass.getMethod(method, Integer.class).invoke(object, resultSet.getInt(1));
						break;
					case "Int":
						entityClass.getMethod(method, int.class).invoke(object, resultSet.getInt(1));
						break;
					}
				}
				resultSet.close();
			}
			statement.close();
		} catch (IllegalAccessException | SQLException | NoSuchMethodException | InvocationTargetException exception) {
			throw new BidsException(ErrorCodesJDBC.CRUD_INSERT_OR_UPDATE_ERROR, exception);
		}
		return object;
	}

	// INSERT
	/**
	 * @param object
	 *            T | Instance of the actual object to insert into the database.
	 * @return T | Instance of the actual object successfully inserted into the database.
	 * @throws BidsException
	 *             BidsException | CRUD_INSERT.
	 */
	@Override
	public T insert(T object) throws BidsException {
		int numberOfParameters = this.fields.size() - this.autogeneratedIdentifiers;
		//System.out.println("insert numberOfParameters=" + numberOfParameters);
		String SQL_INSERT = QueryGenerator.INSERT(tableName, numberOfParameters);
		System.out.println("insert SQL_INSERT=" + SQL_INSERT);
		try {
			return insertOrUpdate(object, SQL_INSERT, false);
		} catch (BidsException BidsException) {
			throw new BidsException(ErrorCodesJDBC.CRUD_INSERT.get(this.getActualClassName()), BidsException);
		}
	}

	// UPDATE
	/**
	 * @param object
	 *            T | Instance of generic object to update into the database.
	 * @return T | Instance of the actual object successfully updated into the database.
	 * @throws BidsException
	 *             BidsException | CRUD_UPDATE.
	 */
	@Override
	public T update(T object) throws BidsException {
		String SQL_UPDATE = QueryGenerator.UPDATE(tableName, generateQueryFields(this.fields.keySet(), true, false, true), generateQueryFields(new LinkedHashSet<>(this.identifiers), true, true, false));
		try {
			return insertOrUpdate(object, SQL_UPDATE, true);
		} catch (BidsException BidsException) {
			throw new BidsException(ErrorCodesJDBC.CRUD_UPDATE.get(this.getActualClassName()), BidsException);
		}
	}

	// DELETE

	/**
	 * @param fields
	 *            Map<String, Object> | Map of the fields as keys and their values as values.
	 * @throws BidsException
	 *             BidsException
	 */
	public void deleteByFields(Map<String, Object> fields) throws BidsException {
		String SQL_DELETE = QueryGenerator.DELETE(tableName, generateQueryFields(fields.keySet(), true, true, false));
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
			setStatementParameters(statement, fields.values());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException sqlException) {
			throw new BidsException(ErrorCodesJDBC.CRUD_DELETE_ERROR.get(this.getActualClassName()), sqlException);
		}
	}

	/**
	 * @param identifiers
	 *            int | Identifier. Requires two parameters when deleting an "Enchere" element: "articleVendu" and "encherisseur".
	 * @throws BidsException
	 *             BidsException | CRUD_DELETE_ERROR.
	 */
	@Override
	public void delete(int... identifiers) throws BidsException {
		Map<String, Object> fields = generateIdentifiersMap(identifiers);
		deleteByFields(fields);
	}

	// SELECT T

	/**
	 * @param query
	 *            String | SQL Query
	 * @param fieldsValues
	 *            Collection<Object> | Fields values to implement in the query.
	 * @return T | Instance of the actual object.
	 * @throws BidsException
	 *             BidsException
	 */
	@Override
	public T selectBy(String query, Collection<Object> fieldsValues) throws BidsException {
		try {
			List<T> instances = selectAllBy(query, fieldsValues);
			return instances.size() > 0 ? instances.get(0) : null; // Get the first element of the list.
		} catch (BidsException exception) {
			throw new BidsException(ErrorCodesJDBC.CRUD_SELECT_BY_ERROR, exception);
		}
	}

	/**
	 * @param fields
	 *            Map<String, Object> | Map of the fields as keys and their values as values.
	 * @return T | Instance of the actual object with the provided identifier(s).
	 * @throws BidsException
	 *             BidsException | CRUD_SELECT_FIELD_ERROR.
	 * @throws SQLException
	 *             SQLException
	 */
	@Override
	public T selectByFields(Map<String, Object> fields) throws BidsException {
		String SQL_SELECT_BY_FIELDS = QueryGenerator.SELECT(generateQueryFields(), tableName, generateQueryFields(fields.keySet(), true, true, false));
		return selectBy(SQL_SELECT_BY_FIELDS, fields.values());
	}

	/**
	 * @param identifiers
	 *            int | Identifier. Must be used with two parameters when selecting a "Bid" element: "item" and "buyer".
	 * @return T | Instance of the actual object with the provided identifier(s).
	 * @throws BidsException
	 *             BidsException | CRUD_SELECT_ID_ERROR.
	 */
	@Override
	public T selectById(int... identifiers) throws BidsException {
		Map<String, Object> fields = generateIdentifiersMap(identifiers);
		try {
			return selectByFields(fields);
		} catch (BidsException exception) {
			throw new BidsException(ErrorCodesJDBC.CRUD_SELECT_ID_ERROR.get(this.getActualClassName()), exception);
		}
	}

	/**
	 * @param field
	 *            String | Name of the field used as a conditional parameter.
	 * @param fieldValue
	 *            Object | Value of the field.
	 * @return T | Instance of the actual object with the provided identifier(s).
	 * @throws BidsException
	 *             BidsException | CRUD_SELECT_FIELD_ERROR.
	 */
	@SuppressWarnings("serial")
	@Override
	public T selectByField(String field, Object fieldValue) throws BidsException {
		Map<String, Object> fields = new HashMap<String, Object>() {
			{
				put(field, fieldValue);
			}
		};
		try {
			return selectByFields(fields);
		} catch (BidsException exception) {
			throw new BidsException(ErrorCodesJDBC.CRUD_SELECT_FIELD_ERROR.get(this.getActualClassName()), exception);
		}
	}

	// SELECT LIST<T>

	/**
	 *
	 * @param query
	 *            String | SQL Query
	 * @param fieldsValues
	 *            Collection<Object> | Fields values to implement in the query.
	 * @return List<T> | List of instances of the actual object.
	 * @throws BidsException
	 *             BidsException
	 */
	@Override
	public List<T> selectAllBy(String query, Collection<Object> fieldsValues) throws BidsException {
		//System.out.println("selectAllBy :query=" + query);

		List<T> instances = new ArrayList<>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			if (fieldsValues != null) {
				setStatementParameters(statement, fieldsValues);
			}
			ResultSet resultSet = statement.executeQuery();
			//System.out.println("selectAllBy :resultSet=" + resultSet);
			while (resultSet.next()) {
				instances.add(generateObject(resultSet));
			}
			resultSet.close();
			statement.close();
			return instances;
		} catch (BidsException | SQLException exception) {
			throw new BidsException(ErrorCodesJDBC.CRUD_SELECT_ALL_BY_ERROR, exception);
		}
	}

	/**
	 * @param fields
	 *            Map<String, Object> | Map of the fields as keys and their values as values.
	 * @return List<T> | List of instances of the actual object.
	 * @throws BidsException
	 *             BidsException
	 * @throws SQLException
	 *             SQLException
	 */
	@Override
	public List<T> selectAllByFields(Map<String, Object> fields) throws BidsException {
		String SQL_SELECT_ALL_BY_FIELDS = QueryGenerator.SELECT(generateQueryFields(), tableName, generateQueryFields(fields.keySet(), true, true, false));
		return selectAllBy(SQL_SELECT_ALL_BY_FIELDS, fields.values());
	}

	/**
	 * @param field
	 *            String | Name of the field used as a conditional parameter.
	 * @param fieldValue
	 *            Object | Value of the field.
	 * @return T | Instance of the actual object with the provided identifier(s).
	 * @throws BidsException
	 *             BidsException | CRUD_SELECT_FIELD_ERROR.
	 */
	@SuppressWarnings("serial")
	@Override
	public List<T> selectAllByField(String field, Object fieldValue) throws BidsException {
		Map<String, Object> fields = new HashMap<String, Object>() {
			{
				put(field, fieldValue);
			}
		};
		try {
			return selectAllByFields(fields);
		} catch (BidsException exception) {
			throw new BidsException(ErrorCodesJDBC.CRUD_SELECT_FIELD_ERROR.get(this.getActualClassName()), exception);
		}
	}

	/**
	 * @return List<T> | List of all the instances of the actual object.
	 * @throws BidsException
	 *             BidsException | CRUD_SELECT_ALL_ERROR.
	 */
	@Override
	public List<T> selectAll() throws BidsException {
		String SQL_SELECT_ALL = QueryGenerator.SELECT_ALL(generateQueryFields(), tableName);
		try (Connection connection = ConnectionProvider.getConnection()) {
			return selectAllBy(SQL_SELECT_ALL, null);
		} catch (SQLException exception) {
			throw new BidsException(ErrorCodesJDBC.CRUD_SELECT_ALL_ERROR.get(this.getActualClassName()), exception);
		}
	}

	// METHODS

	/**
	 * @param resultSet
	 *            | ResultSet
	 * @return T | Instance of the actual object with the data of the ResultSet.
	 * @throws BidsException
	 *             BidsException | GENERATE_OBJECT_ERROR.
	 */
	@SuppressWarnings("unchecked")
	private T generateObject(ResultSet resultSet) throws BidsException {
		T instance = getObject();
		try {
			for (Map.Entry<String, String> field : this.fields.entrySet()) {
				String method = "set" + field.getKey().substring(0, 1).toUpperCase() + field.getKey().substring(1);
				//System.out.println("generateObject, method=" + method);
				//System.out.println("generateObject, field.getValue()=" + field.getValue());
				switch (field.getValue()) {
				case "String":
					entityClass.getMethod(method, String.class).invoke(instance, resultSet.getString(field.getKey()));
					break;
				case "Integer":
					entityClass.getMethod(method, Integer.class).invoke(instance, resultSet.getInt(field.getKey()));
					break;
				case "Int":
					entityClass.getMethod(method, int.class).invoke(instance, resultSet.getInt(field.getKey()));
					break;
				case "Byte":
					entityClass.getMethod(method, byte.class).invoke(instance, resultSet.getByte(field.getKey()));
					break;
				case "LocalDateTime":
					entityClass.getMethod(method, LocalDateTime.class).invoke(instance, resultSet.getTimestamp(field.getKey()).toLocalDateTime());
					break;
				default:
					//System.out.println("generateObject, method=" + method);
					//System.out.println("generateObject, field.getValue()=" + field.getValue());
					int identifier = resultSet.getInt(field.getKey());
					//System.out.println("generateObject, identifier=" + identifier);
					GenericJDBCDAOImpl<T> implementation = (GenericJDBCDAOImpl<T>) Class.forName(field.getValue()).newInstance();
					entityClass.getMethod(method, implementation.getObject().getClass()).invoke(instance, implementation.selectById(identifier));
				}
			}
		} catch (IllegalAccessException | SQLException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | InstantiationException exception) {
			throw new BidsException(ErrorCodesJDBC.GENERATE_OBJECT_ERROR.get(this.getActualClassName()), exception);
		}
		return instance;
	}

	/**
	 * @param statement
	 *            PreparedStatement | Statement in which the data will be added.
	 * @param object
	 *            T | Instance of the actual object providing the data.
	 * @param isUpdate
	 *            boolean | "true" if the statement is about an update.
	 * @throws BidsException
	 *             BidsException | GENERATE_STATEMENT_DATA_ERROR.
	 */
	private void setStatementParameters(PreparedStatement statement, T object, boolean isUpdate) throws BidsException {
		try {
			//System.out.println("setStatementParameters+ statement=" + statement + ", isUpdate=" + isUpdate);
			//System.out.println("object=" + object);
			int parameterIndex = 1;
			int skips = this.autogeneratedIdentifiers;
			for (Map.Entry<String, String> field : this.fields.entrySet()) {
				if (skips != 0) {
					skips--;
					continue;
				}
				String method = "get" + field.getKey().substring(0, 1).toUpperCase() + field.getKey().substring(1);
				//System.out.println("method 1 =" + method);
				//System.out.println("parameterIndex=" + parameterIndex);
				switch (field.getValue()) {
				case "String":
				case "Integer":
				case "Int":
				case "Byte":
					statement.setObject(parameterIndex, entityClass.getMethod(method).invoke(object));
					break;
				case "LocalDateTime":
					LocalDateTime localDateTime = (LocalDateTime) entityClass.getMethod(method).invoke(object);
					statement.setTimestamp(parameterIndex, Timestamp.valueOf(localDateTime));
					break;
				default:
					method = "get" + field.getKey().substring(0, 1).toUpperCase() + field.getKey().substring(1);
					//System.out.println("method (default) =" + method);
					int noIdentifier = (int) entityClass.getMethod(method).invoke(object);
					if (noIdentifier != 0) {
						statement.setInt(parameterIndex, (int) entityClass.getMethod(method).invoke(object));
					} else {
						statement.setNull(parameterIndex, Types.INTEGER);
					}
					break;
				}
				parameterIndex++;
			}
			if (isUpdate) {
				for (String identifier : this.identifiers) {
					String method = "get" + identifier.substring(0, 1).toUpperCase() + identifier.substring(1);
					//System.out.println("method (isUpdate) =" + method);
					//System.out.println("parameterIndex=" + parameterIndex);
					statement.setInt(parameterIndex, (int) entityClass.getMethod(method).invoke(object));
					parameterIndex++;
				}
			}
		} catch (InvocationTargetException | NoSuchMethodException | SQLException | IllegalAccessException exception) {
			throw new BidsException(ErrorCodesJDBC.GENERATE_STATEMENT_DATA_ERROR.get(this.getActualClassName()), exception);
		}
	}

	/**
	 *
	 * @param statement
	 *            PreparedStatement | Statement in which the data will be added.
	 * @param fieldsValues
	 *            Collection<Object> | Collection of field values.
	 * @throws BidsException
	 *             BidsException | GENERATE_STATEMENT_DATA_ERROR.
	 */
	private void setStatementParameters(PreparedStatement statement, Collection<Object> fieldsValues) throws BidsException {
		try {
			int parameterIndex = 1;
			for (Object fieldValue : fieldsValues) {
				if (fieldValue instanceof LocalDateTime) {
					statement.setTimestamp(parameterIndex, Timestamp.valueOf((LocalDateTime) fieldValue));
				} else {
					statement.setObject(parameterIndex, fieldValue);
				}
				parameterIndex++;
			}
		} catch (SQLException sqlException) {
			throw new BidsException(ErrorCodesJDBC.GENERATE_STATEMENT_DATA_ERROR.get(this.getActualClassName()), sqlException);
		}
	}

	/**
	 * @return String | Simple name of the actual class T.
	 */
	protected String getActualClassName() {
		return this.entityClass.getSimpleName();
	}

	/**
	 * @param fields
	 *            Set<String> | Set of fields.
	 * @param isUnknownParameter
	 *            boolean | "true" if the fields are used with a "= ?" placeholder.
	 * @param isCondition
	 *            boolean | "true" if the fields are used as conditions.
	 * @param isUpdate
	 *            boolean | "true" if the fields are used for an update.
	 * @return String | Formatted fields used for a query.
	 */
	protected String generateQueryFields(Set<String> fields, boolean isUnknownParameter, boolean isCondition, boolean isUpdate) {
		StringBuilder fieldsSelection = new StringBuilder();
		String separator = "";
		for (String field : fields) {
			if (this.identifiers.contains(field) && isUpdate) {
				continue;
			}
			fieldsSelection.append(separator).append(field);
			if (isUnknownParameter) {
				fieldsSelection.append(" = ?");
			}
			separator = isCondition ? " AND " : ", ";
		}
		return fieldsSelection.toString();
	}

	protected String generateQueryFields() {
		return generateQueryFields(this.fields.keySet(), false, false, false); // Default values.
	}

	/**
	 * Generate a map with the identifiers fields names as keys, and the given arguments as values.
	 */
	protected Map<String, Object> generateIdentifiersMap(int... identifiers) {
		Map<String, Object> fields = new HashMap<String, Object>();
		int index = 0;
		//System.out.println("generateIdentifiersMap, this.identifiers=" + this.identifiers);
		for (int identifier : identifiers) {
			//System.out.println("generateIdentifiersMap, identifier=" + identifier);
			fields.put(this.identifiers.get(index), identifier);
			index++;
		}
		//System.out.println("generateIdentifiersMap, fields=" + fields);
		return fields;
	}
}
