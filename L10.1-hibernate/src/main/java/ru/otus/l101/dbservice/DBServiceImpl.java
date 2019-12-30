package ru.otus.l101.dbservice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.otus.l101.connection.ConnectionHelper;
import ru.otus.l101.dao.*;
import ru.otus.l101.dataset.*;

public class DBServiceImpl implements DBService {

	private Connection connection;

	public DBServiceImpl() throws SQLException {
		this.connection = ConnectionHelper.getConnection();
	}

	@Override
	public void save(UserDataSet user) throws SQLException {
		try {
			connection.setAutoCommit(false);
			// Save user
			UserDAO userDao = new UserDAOImpl(connection);
			userDao.save(user);
			// Save address
			AddressDataSet address = user.getAddress();
			if (address != null) {
				AddressDAO addressDao = new AddressDAOImpl(connection);
				addressDao.save(address);
			}
			// Save phones
			PhoneDAO phoneDao = new PhoneDAOImpl(connection);
			for (PhoneDataSet phone : user.getPhones()) {
				phoneDao.save(phone);
			}
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Override
	public void delete(UserDataSet user) throws SQLException {
		try {
			connection.setAutoCommit(false);
			// Delete address
			AddressDataSet address = user.getAddress();
			if (address != null) {
				AddressDAO addressDao = new AddressDAOImpl(connection);
				addressDao.delete(address);
			}
			// Delete phones
			PhoneDAO phoneDao = new PhoneDAOImpl(connection);
			for (PhoneDataSet phone : user.getPhones()) {
				phoneDao.delete(phone);
			}
			// Delete user
			UserDAO userDao = new UserDAOImpl(connection);
			userDao.delete(user);
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Override
	public UserDataSet load(Long id) throws SQLException {
		UserDataSet user = null;
		try {
			connection.setAutoCommit(false);
			UserDAO userDao = new UserDAOImpl(connection);
			user = userDao.load(id);
			loadAddress(user);
			loadPhones(user);
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
		return user;
	}

	@Override
	public UserDataSet loadByName(String name) throws SQLException {
		UserDataSet user = null;
		try {
			connection.setAutoCommit(false);
			UserDAO userDao = new UserDAOImpl(connection);
			user = userDao.loadByName(name);
			loadAddress(user);
			loadPhones(user);
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
		return user;
	}

	@Override
	public List<UserDataSet> loadAll() throws SQLException {
		List<UserDataSet> users = new ArrayList<>();
		try {
			connection.setAutoCommit(false);
			UserDAO userDao = new UserDAOImpl(connection);
			users = userDao.loadAll();
			for (UserDataSet user : users) {
				loadAddress(user);
				loadPhones(user);
			}
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
		return users;
	}

	@Override
	public void shutdown() throws SQLException {
		connection.close();
	}

	private void loadAddress(UserDataSet user) throws SQLException {
		AddressDAO addressDao = new AddressDAOImpl(connection);
		AddressDataSet address = addressDao.loadByUserId(user.getId());
		user.setAddress(address);
	}

	private void loadPhones(UserDataSet user) throws SQLException {
		PhoneDAO phoneDao = new PhoneDAOImpl(connection);
		List<PhoneDataSet> phones = phoneDao.loadByUserId(user.getId());
		user.setPhones(phones);
	}

}
