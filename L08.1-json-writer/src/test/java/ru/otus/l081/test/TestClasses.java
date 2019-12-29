package ru.otus.l081.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class TestPrimitives {
	byte age = 25;
	short year = 2018;
	int number = 123456;
	long key = 250L;
	float ratio = 0.5f;
	double price = 105.99;
	boolean isCorrect = true;
	char symbol = 'a';
	transient String text = "Transient Variable";
}

class TestObjects {
	private static String myString = "Static Variable";
	Integer number = 1;
	StringBuilder str = new StringBuilder("Test String");
}

class TestArrays {
	int[][] matrix = { { 5, 3, 10 }, { 4, 9 }, { 7, 23 } };
	String[] names = { "John", "Jane", "Judy" };
}

class TestCollections {
	transient List<String> friends = new ArrayList<>(Arrays.asList("Anna", "Ella", "Mary"));
	transient Set<Integer> digits = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0));
	Map<Integer, String> contacts = new HashMap<>();
	{
		Iterator<Integer> iterator = digits.iterator();
		int index = 0;
		while (iterator.hasNext() && index < friends.size()) {
			contacts.put(iterator.next(), friends.get(index++));
		}
	}
}
