package fr.eni.bids.dal;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import fr.eni.bids.BidsException;

public interface DAO<T> {

	public List<String> identifiers = null;

	public T insert(T object) throws BidsException;

	public T update(T object) throws BidsException;

	public void deleteByFields(Map<String, Object> fields) throws BidsException;

	public void delete(int... identifiers) throws BidsException;

	public T selectBy(String query, Collection<Object> fieldsValues) throws BidsException;

	public T selectById(int... identifiers) throws BidsException;

	public T selectByField(String field, Object fieldValue) throws BidsException;

	public T selectByFields(Map<String, Object> fields) throws BidsException;

	public List<T> selectAll() throws BidsException;

	public List<T> selectAllBy(String query, Collection<Object> fieldsValues) throws BidsException;

	public List<T> selectAllByField(String field, Object fieldValue) throws BidsException;

	public List<T> selectAllByFields(Map<String, Object> fields) throws BidsException;
}