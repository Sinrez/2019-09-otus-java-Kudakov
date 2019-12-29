package ru.otus.homework;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDB implements DB {
    private List<Memento> data;

    public InMemoryDB() {
        this.data = new ArrayList<>();
    }

    @Override
    public void create(Memento memento) {
        data.add(memento);
    }

    @Override
    public Memento read(int index) {
        return data.get(index);
    }
}
