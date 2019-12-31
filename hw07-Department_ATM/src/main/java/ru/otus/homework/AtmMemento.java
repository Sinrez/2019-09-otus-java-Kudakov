package ru.otus.homework;

import ru.otus.homework.bills.Bill;
import ru.otus.homework.cassettes.Cassette;

import java.util.Map;

public class AtmMemento implements Memento {

    private Map<Bill, Cassette> startState;

    public AtmMemento(Map<Bill, Cassette> startState) {
        this.startState = startState;
    }

    public Map<Bill, Cassette> getStartState() {
        return startState;
    }

}
