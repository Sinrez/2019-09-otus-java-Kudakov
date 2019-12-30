package ru.otus.l101.dbservice;

import java.sql.SQLException;
import java.util.List;

import ru.otus.l101.dataset.UserDataSet;

public interface DBService {

	void save(UserDataSet user) throws SQLException;

	UserDataSet load(Long id) throws SQLException;

	UserDataSet loadByName(String name) throws SQLException;

	List<UserDataSet> loadAll() throws SQLException;

	void delete(UserDataSet user) throws SQLException;

	void shutdown() throws SQLException;
}
