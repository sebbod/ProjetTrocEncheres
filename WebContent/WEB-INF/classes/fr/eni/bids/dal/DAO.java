package fr.eni.bids.dal;

import java.util.List;

public interface DAO<T> {

	// Sélectionner un business object par son id
	public T getById(Integer id) throws DALException;

	// Sélectionner tous les business objects
	public List<T> getAll() throws DALException;

	// Insérer un nouveau business object
	public void insert(T data) throws DALException;

	// Modifier les attributs d'un business object
	public void update(T data) throws DALException;

	// Supprimer un business object
	public void delete(T obj) throws DALException;

}