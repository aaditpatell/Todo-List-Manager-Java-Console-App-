# Todo-List-Manager-Java-Console-App-
A simple Java console app to manage tasks with optional due dates and file persistence. Add, complete, delete, and list tasks through an interactive menu. Data is stored in a CSV file, loading automatically on start. Lightweight and dependency-free, it showcases Java OOP, collections, and file I/O in a compact project.




The Todo List Manager is a lightweight Java console application designed to showcase practical object-oriented programming concepts while remaining highly usable, created to help me further learn Java. It provides a simple, text-based interface for managing tasks with optional due dates and supports automatic persistence through CSV file storage. This means tasks you add, complete, or delete are remembered between sessions without requiring any database or external dependencies. By focusing on clean design, modular structure, and core Java features, this project demonstrates effective use of classes, collections, file I/O, and input handling. It is an ideal starting point for beginners learning Java or anyone seeking a compact utility that strikes a balance between simplicity and functionality. The project can also serve as a foundation for future improvements such as GUI integration, cloud sync, or task prioritization.

Key Features

Interactive Menu: Intuitive numbered options to add, complete, delete, list, or quit.

Task Management: Each task includes an ID, title, optional due date, and completion status.

Persistence: Tasks are saved to and loaded from a CSV file (data/tasks.csv) automatically.

Sorting: Incomplete tasks appear before completed ones, ordered by due date and title.

Lightweight: No dependencies outside of standard Java libraries; runs on Java 11+.

Cross-Platform: Works on any system with a Java runtime (Linux, macOS, Windows).

Expandable Design: Separate classes (Task, TodoList, Storage, Main) make it easy to extend.

Educational Value

Demonstrates object-oriented design through encapsulation and immutability.

Practice collections (ArrayList, sorting, and iteration).

Implements file I/O for real-world data persistence.

Uses LocalDate for handling optional due dates.

Shows practical user input handling with validation.

This project balances accessibility and depth, making it a showcase of core Java skills while remaining practical as a day-to-day task manager.
