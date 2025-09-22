package com.example.todolist;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage(Paths.get("data", "tasks.csv"));
        TodoList list = new TodoList();
        list.clearAndLoad(storage.load());
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("1) Add task");
            System.out.println("2) Complete task");
            System.out.println("3) Delete task");
            System.out.println("4) List tasks");
            System.out.println("5) Quit");
            System.out.print("> ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.print("Title: ");
                    String title = sc.nextLine().trim();
                    System.out.print("Due date (YYYY-MM-DD, optional): ");
                    String dueStr = sc.nextLine().trim();
                    LocalDate due = null;
                    if (!dueStr.isEmpty()) {
                        try { due = LocalDate.parse(dueStr); } catch (Exception e) { System.out.println("Invalid date."); }
                    }
                    list.add(title, due);
                    storage.save(list.all());
                    System.out.println("Added.");
                    break;
                case "2":
                    print(list.all());
                    System.out.print("Enter task id to complete: ");
                    long idc = parseLong(sc.nextLine());
                    Optional<Task> done = list.complete(idc);
                    storage.save(list.all());
                    System.out.println(done.isPresent() ? "Completed." : "Not found.");
                    break;
                case "3":
                    print(list.all());
                    System.out.print("Enter task id to delete: ");
                    long idd = parseLong(sc.nextLine());
                    boolean removed = list.remove(idd);
                    storage.save(list.all());
                    System.out.println(removed ? "Deleted." : "Not found.");
                    break;
                case "4":
                    print(list.all());
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Choose 1-5.");
            }
        }
        sc.close();
    }

    private static void print(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks.");
            return;
        }
        for (Task t : tasks) System.out.println(t.toString());
    }

    private static long parseLong(String s) {
        try { return Long.parseLong(s.trim()); } catch (Exception e) { return -1; }
    }
}
