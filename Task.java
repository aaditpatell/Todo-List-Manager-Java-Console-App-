package com.example.todolist;

import java.time.LocalDate;
import java.util.Objects;

public class Task implements Comparable<Task> {
    private final long id;
    private final String title;
    private final LocalDate dueDate;
    private final boolean completed;

    public Task(long id, String title, LocalDate dueDate, boolean completed) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isCompleted() { return completed; }
    public Task complete() { return new Task(id, title, dueDate, true); }

    @Override
    public int compareTo(Task o) {
        if (this.completed != o.completed) return this.completed ? 1 : -1;
        if (this.dueDate == null && o.dueDate != null) return 1;
        if (this.dueDate != null && o.dueDate == null) return -1;
        if (this.dueDate != null && o.dueDate != null) {
            int c = this.dueDate.compareTo(o.dueDate);
            if (c != 0) return c;
        }
        return this.title.compareToIgnoreCase(o.title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id == task.id && completed == task.completed && Objects.equals(title, task.title) && Objects.equals(dueDate, task.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, dueDate, completed);
    }

    @Override
    public String toString() {
        String s = completed ? "[x]" : "[ ]";
        String d = dueDate == null ? "" : " (due " + dueDate + ")";
        return s + " " + title + d + " #" + id;
    }
}
