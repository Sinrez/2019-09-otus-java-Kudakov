package ru.otus.homework;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DIYarrayListTest {
    private static final String ZERO_ELEMENT = "0";
    private static List<String> stringList;
    private DIYarrayList<String> stringDIYList;

    @BeforeEach
    public void init() {
        stringDIYList = new DIYarrayList<>();
        stringList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            stringList.add(String.valueOf(i));
        }
    }

    @Test
    void size_Null() {
        assertEquals(0, stringDIYList.size());
    }

    @Test
    void size_notNull() {
        stringDIYList.add(ZERO_ELEMENT);
        assertEquals(1, stringDIYList.size());
    }

    @Test
    void isEmpty() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.isEmpty()
        );
    }

    @Test
    void contains() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.contains("1")
        );
    }

    @Test
    void iterator() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.iterator()
        );
    }

    @Test
    void toArray() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.toArray()
        );
    }

    @Test
    void toArray1() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.toArray(new String[]{})
        );
    }

    @Test
    void add() {
        stringDIYList.add(ZERO_ELEMENT);
        stringDIYList.add("1");
        assertEquals(ZERO_ELEMENT, stringDIYList.get(0));
        assertEquals("1", stringDIYList.get(1));
    }

    @Test
    void remove() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.remove("1")
        );
    }

    @Test
    void containsAll() {

        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.containsAll(new ArrayList<>())
        );
    }

    @Test
    void addAll_stringDIYLitIsNotNull() {
        stringDIYList.add(ZERO_ELEMENT);
        int expectedSizeAfterAddAll = stringDIYList.size() + stringList.size();
        stringDIYList.addAll(stringList);
        assertEquals(expectedSizeAfterAddAll, stringDIYList.size());
        assertEquals(ZERO_ELEMENT, stringDIYList.get(0));
        for (int i = 0; i < stringList.size(); i++) {
            assertEquals(
                    String.valueOf(i), stringDIYList.get(i + 1)
            );
        }
    }

    @Test
    void addAll_stringDIYLitIsNull() {
        int expectedSize = stringList.size();
        stringDIYList.addAll(stringList);
        assertEquals(expectedSize, stringDIYList.size());
        for (int i = 0; i < stringList.size(); i++) {
            assertEquals(stringList.get(i), stringDIYList.get(i));
        }
    }

    @Test
    void removeAll() {

        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.removeAll(new ArrayList<>())
        );
    }

    @Test
    void retainAll() {

        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.retainAll(new ArrayList<>())
        );
    }

    @Test
    void clear() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.clear()
        );
    }

    @Test
    void get() {
        stringDIYList.add("element");
        assertEquals("element",stringDIYList.get(0));
    }

    @Test
    void set() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.set(0,"1")
        );
    }

    @Test
    void add1() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.add(0,"0")
        );
    }

    @Test
    void remove1() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.remove(1)
        );
    }

    @Test
    void indexOf() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.indexOf("1")
        );
    }

    @Test
    void lastIndexOf() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.lastIndexOf("11")
        );
    }

    @Test
    void listIterator() {
        new DIYarrayList<>().listIterator();
    }

    @Test
    void listIterator1() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.listIterator(9)
        );
    }

    @Test
    void subList() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> stringDIYList.subList(1,3)
        );
    }

    @Test
    void test_toString() {
        DIYarrayList<String> test=  new DIYarrayList<>();
        test.add("1");
        test.add("2");
        assertEquals("DIYarrayList{thisArray=[1,2]}",test.toString());
    }
}