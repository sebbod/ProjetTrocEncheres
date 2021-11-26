package fr.eni.bids.bll;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import fr.eni.bids.BidsException;
import fr.eni.bids.dal.DAO;
import fr.eni.bids.dal.DAOFactory;

public abstract class GenericManager<T> {
	protected Class<T> entityClass;
	protected DAO DAOBusinessObject;

	// CONSTRUCTOR
	public GenericManager() throws BidsException {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		try {
			DAOBusinessObject = DAOFactory.getBusinessObjectDAO(this.getActualClassName());
		} catch (BidsException BidsException) {
			throw new BidsException(ErrorCodesBLL.INITIALIZATION_DAO_ERROR.get(this.getActualClassName()), BidsException);
		}
	}

	/**
	 * @return String | Simple name of the actual class T.
	 */
	private String getActualClassName() {
		return this.entityClass.getSimpleName();
	}

	// CRUD

	/**
	 * SELECT List<T>
	 */
	public List<T> getAll() throws BidsException {
		try {
			return DAOBusinessObject.selectAll();
		} catch (BidsException BidsException) {
			throw new BidsException(ErrorCodesBLL.GET_ALL_ERROR.get(this.getActualClassName()), BidsException);
		}
	}

	/**
	 * SELECT T
	 */
	public T getById(int... identifiers) throws BidsException {
		try {
			return (T) DAOBusinessObject.selectById(identifiers);
		} catch (BidsException BidsException) {
			throw new BidsException(ErrorCodesBLL.GET_BY_ID_ERROR.get(this.getActualClassName()), BidsException);
		}
	}

	public T getByField(String field, Object fieldValue) throws BidsException {
		try {
			return (T) DAOBusinessObject.selectByField(field, fieldValue);
		} catch (BidsException BidsException) {
			throw new BidsException(ErrorCodesBLL.GET_BY_FIELD_ERROR.get(this.getActualClassName()), BidsException);
		}
	}

	/**
	 * INSERT T
	 */
	public T add(T object) throws BidsException {
		try {
			boolean alreadyExists = checkUnity(object);
			if (alreadyExists) {
				throw new BidsException(ErrorCodesBLL.ADD_ALREADY_EXIST_ERROR.get(getActualClassName()));
			}
			checkAttributes(object);
			object = (T) DAOBusinessObject.insert(object);
			executeUpdate(object, "INSERT");
			return object;
		} catch (BidsException BidsException) {
			throw new BidsException(ErrorCodesBLL.ADD_ERROR.get(this.getActualClassName()), BidsException);
		}
	}

	/**
	 * UPDATE T
	 */
	public T update(T object) throws BidsException {
		try {
			boolean alreadyExists = checkUnity(object);
			System.out.println("alreadyExists=" + alreadyExists);
			if (!alreadyExists) {
				throw new BidsException(ErrorCodesBLL.UPDATE_NOT_EXIST_ERROR.get(getActualClassName()));
			}
			checkAttributes(object);
			object = (T) DAOBusinessObject.update(object);
			executeUpdate(object, "UPDATE");
			return object;
		} catch (BidsException BidsException) {
			throw new BidsException(ErrorCodesBLL.UPDATE_ERROR.get(this.getActualClassName()), BidsException);
		}
	}

	/**
	 * DELETE T
	 */
	private void delete(T object, int... identifiers) throws BidsException {
		try {
			boolean alreadyExists = checkUnity(object);
			if (!alreadyExists) {
				throw new BidsException(ErrorCodesBLL.DELETE_NOT_EXIST_ERROR.get(getActualClassName()));
			}
			DAOBusinessObject.delete(identifiers);
			executeUpdate(object, "DELETE");
		} catch (BidsException BidsException) {
			throw new BidsException(ErrorCodesBLL.DELETE_ERROR.get(this.getActualClassName()), BidsException);
		}
	}

	public void delete(T object) throws BidsException {
		int[] identifiers = getIdentifiers(object);
		delete(object, identifiers);
	}

	public void delete(int... identifiers) throws BidsException {
		T object = getById(identifiers);
		delete(object, identifiers);
	}

	// ABSTRACT METHODS

	protected abstract int[] getIdentifiers(T object);

	protected abstract void executeUpdate(T object, String operationCRUD) throws BidsException;

	protected abstract boolean checkUnity(T Object) throws BidsException;

	protected abstract void checkAttributes(T Object) throws BidsException;
}
