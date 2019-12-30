package ru.otus.l101.dbservice;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import ru.otus.l101.dao.*;
import ru.otus.l101.dataset.*;

public class DBServiceHibernateImpl implements DBService {

	private final SessionFactory sessionFactory;

	public DBServiceHibernateImpl() {
		Configuration configuration = new Configuration();

		configuration.addAnnotatedClass(UserDataSet.class);
		configuration.addAnnotatedClass(PhoneDataSet.class);
		configuration.addAnnotatedClass(AddressDataSet.class);

		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/orm");
		configuration.setProperty("hibernate.connection.username", "postgres");
		configuration.setProperty("hibernate.connection.password", "admin");
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.hbm2ddl.auto", "create");
		configuration.setProperty("hibernate.connection.useSSL", "false");
		configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

		sessionFactory = createSessionFactory(configuration);
	}

	public DBServiceHibernateImpl(Configuration configuration) {
		sessionFactory = createSessionFactory(configuration);
	}

	private static SessionFactory createSessionFactory(Configuration configuration) {
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = builder.build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	@Override
	public void save(UserDataSet user) throws SQLException {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			UserDAO userDao = new UserDAOHibernateImpl(session);
			userDao.save(user);
			transaction.commit();
		}
	}

	@Override
	public void delete(UserDataSet user) throws SQLException {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			UserDAO dao = new UserDAOHibernateImpl(session);
			dao.delete(user);
			transaction.commit();
		}
	}

	@Override
	public UserDataSet load(Long id) throws SQLException {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			UserDAO dao = new UserDAOHibernateImpl(session);
			UserDataSet result = dao.load(id);
			transaction.commit();
			return result;
		}
	}

	@Override
	public UserDataSet loadByName(String name) throws SQLException {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			UserDAO dao = new UserDAOHibernateImpl(session);
			UserDataSet result = dao.loadByName(name);
			transaction.commit();
			return result;
		}
	}

	@Override
	public List<UserDataSet> loadAll() throws SQLException {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			UserDAO dao = new UserDAOHibernateImpl(session);
			List<UserDataSet> result = dao.loadAll();
			transaction.commit();
			return result;
		}
	}

	@Override
	public void shutdown() {
		sessionFactory.close();
	}

	public AddressDataSet loadAddressByUserId(Long userId) throws SQLException {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			AddressDAO dao = new AddressDAOHibernateImpl(session);
			AddressDataSet result = dao.loadByUserId(userId);
			transaction.commit();
			return result;
		}
	}

	public List<PhoneDataSet> loadPhonesByUserId(Long userId) throws SQLException {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			PhoneDAO dao = new PhoneDAOHibernateImpl(session);
			List<PhoneDataSet> result = dao.loadByUserId(userId);
			transaction.commit();
			return result;
		}
	}

}
