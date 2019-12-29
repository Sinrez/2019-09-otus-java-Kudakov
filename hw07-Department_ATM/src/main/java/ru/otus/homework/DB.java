package ru.otus.homework;

public interface DB {
    void create(Memento memento);

    Memento read(int index);
}