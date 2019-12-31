package ru.otus.homework;

import ru.otus.homework.bills.Bill;
import ru.otus.homework.cassettes.Cassette;

import java.util.Map;

public interface Memento {
    Map<Bill, Cassette> getStartState();
}
