package ru.otus.homework;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionsTest {

    private static List<String> stringList;
    private DIYarrayList<String> stringDIYList;
    private String[] stringArray;
    private DIYarrayList<String> destinationStringDIYarrayList;
    private ArrayList<String> stringArrayList;

    @BeforeEach
    public void beforeEach() {
        stringDIYList = new DIYarrayList<>();
        stringList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            stringList.add(String.valueOf(i));
        }
        stringDIYList.addAll(stringList);
        stringArray = new String[1000];
        for (int i = 1000; i < 2000; i++) {
            stringArray[i - 1000] = String.valueOf(i);
        }
        destinationStringDIYarrayList = createEmptyDIYarrayList(1000);
        stringArrayList = createEmptyArrayList();
    }

    private DIYarrayList<String> createEmptyDIYarrayList(int size) {
        DIYarrayList<String> emptyDIYarrayList = new DIYarrayList<>();
        for (int i = 0; i < size; i++) {
            emptyDIYarrayList.add(null);
        }
        return emptyDIYarrayList;
    }

    private ArrayList<String> createEmptyArrayList() {
        ArrayList<String> emptyArrayList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            emptyArrayList.add(null);
        }
        return emptyArrayList;
    }

    @Test
    void addAll() {
        int expectedSizeAfterAddAll = stringDIYList.size() + stringArray.length;
        Collections.addAll(stringDIYList, stringArray);
        Collections.addAll(stringList, stringArray);
        assertEquals(expectedSizeAfterAddAll, stringDIYList.size());
        for (int i = 0; i < expectedSizeAfterAddAll; i++) {
            assertEquals(stringDIYList.get(i), stringList.get(i));
        }
    }

    @Test
    void copy__DIYArrayList_To_DIYArrayList() {
        Collections.copy(destinationStringDIYarrayList, stringDIYList);
        assertEquals(destinationStringDIYarrayList.size(), stringDIYList.size());
        for (int i = 0; i < stringDIYList.size(); i++) {
            assertEquals(stringDIYList.get(i), destinationStringDIYarrayList.get(i));
        }
    }

    @Test
    void copy__DIYArrayList_To_ArrayList() {
        Collections.copy(stringArrayList, stringDIYList);
        assertEquals(stringArrayList.size(), stringDIYList.size());
        for (int i = 0; i < stringDIYList.size(); i++) {
            assertEquals(stringDIYList.get(i), stringArrayList.get(i));
        }
    }

    @Test
    void copy___ArrayList_To_DIYArrayList() {
        Collections.copy(destinationStringDIYarrayList, stringList);
        assertEquals(destinationStringDIYarrayList.size(), stringList.size());
        for (int i = 0; i < stringDIYList.size(); i++) {
            assertEquals(stringList.get(i), destinationStringDIYarrayList.get(i));
        }
    }
}