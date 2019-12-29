package ru.otus.l081.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import ru.otus.l081.writer.JsonObjectWriter;

class JsonObjectWriterTest {

	JsonObjectWriter writer;
	Gson gson;

	@BeforeEach
	void setUp() throws Exception {
		writer = new JsonObjectWriter();
		gson = new Gson();
	}

	@AfterEach
	void tearDown() throws Exception {
		writer = null;
		gson = null;
	}

	@Test
	void testWriteToJsonPrimitives() {
		TestPrimitives object = new TestPrimitives();
		assertEquals(gson.toJson(object), writer.writeToJson(object));
	}

	@Test
	void testWriteToJsonObjects() {
		TestObjects object = new TestObjects();
		assertEquals(gson.toJson(object), writer.writeToJson(object));
	}

	@Test
	void testWriteToJsonArrays() {
		TestArrays object = new TestArrays();
		assertEquals(gson.toJson(object), writer.writeToJson(object));
	}

	@Test
	void testWriteToJsonCollections() {
		TestCollections object = new TestCollections();
		assertEquals(gson.toJson(object), writer.writeToJson(object));
	}

}
