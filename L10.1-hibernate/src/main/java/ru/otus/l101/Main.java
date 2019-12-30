package ru.otus.l101;

import java.sql.SQLException;
import java.util.List;

import ru.otus.l101.dataset.*;
import ru.otus.l101.dbservice.*;

public class Main {

	public static void main(String[] args) throws SQLException {
		System.out.println("Hibernate");
		testService(new DBServiceHibernateImpl());

		System.out.println("myORM");
		testService(new DBServiceImpl());
	}

	private static void testService(DBService service) throws SQLException {
		System.out.println("Creating a user:");
		UserDataSet user = new UserDataSet("Johnny Dohn", 35);
		user.setAddress(new AddressDataSet("My Green Street"));
		user.addPhone(new PhoneDataSet("01475558369"));
		user.addPhone(new PhoneDataSet("0125556789"));
		service.save(user);
		System.out.println("User created: " + user);

		System.out.println("Creating a user:");
		user = new UserDataSet("John Conar", 25);
		user.setAddress(new AddressDataSet("My Wide Street"));
		user.addPhone(new PhoneDataSet("078955556123"));
		user.addPhone(new PhoneDataSet("0364449258147"));
		service.save(user);
		System.out.println("User created: " + user);

		System.out.println("Loading all users:");
		List<UserDataSet> users = service.loadAll();
		System.out.println("Users in DB: " + users.size());

		for (UserDataSet theUser : service.loadAll()) {
			System.out.println(theUser);
		}

		System.out.println("Deleting users:");
		for (UserDataSet theUser : service.loadAll()) {
			service.delete(theUser);
			System.out.println("User deleted: " + theUser);
		}

		System.out.println("Users in DB: " + service.loadAll().size());

		service.shutdown();
	}

}
