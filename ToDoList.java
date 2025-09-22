package com.example.todolist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class TodoList {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(1);

    public List<Task> all() {
        List<Task> copy = new ArrayList<>(tasks);
        Collections.sort(copy);
        return copy;
    }

    public Task add(String title, LocalDate due) {
        Task t = new Task(seq.getAndIncrement(), title, due, false);
        tasks.add(t);
        return t;
    }

    public boolean remove(long id) {
        return tasks.removeIf(t -> t.getId() == id);
    }

    public Optional<Task> complete(long id) {
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getId() == id) {
                Task c = t.complete();
                tasks.set(i, c);
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    public void clearAndLoad(List<Task> loaded) {
        tasks.clear();
        long max = 0;
        for (Task t : loaded) {
            tasks.add(t);
            if (t.getId() > max) max = t.getId();
        }
        seq.set(max + 1);
    }
}
