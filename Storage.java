package com.example.todolist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path path;

    public Storage(Path path) {
        this.path = path;
    }

    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            File f = path.toFile();
            if (!f.exists()) {
                Files.createDirectories(path.getParent());
                f.createNewFile();
                return list;
            }
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = split(line);
                    long id = Long.parseLong(parts[0]);
                    String title = unescape(parts[1]);
                    LocalDate due = parts[2].isEmpty() ? null : LocalDate.parse(parts[2]);
                    boolean completed = Boolean.parseBoolean(parts[3]);
                    list.add(new Task(id, title, due, completed));
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void save(List<Task> tasks) {
        try {
            Files.createDirectories(path.getParent());
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile()))) {
                for (Task t : tasks) {
                    String id = Long.toString(t.getId());
                    String title = escape(t.getTitle());
                    String due = t.getDueDate() == null ? "" : t.getDueDate().toString();
                    String completed = Boolean.toString(t.isCompleted());
                    bw.write(String.join("|", id, title, due, completed));
                    bw.newLine();
                }
            }
        } catch (Exception e) {
        }
    }

    private String escape(String s) {
        return s.replace("\\", "\\\\").replace("|", "\\|").replace("\n", "\\n");
    }

    private String unescape(String s) {
        StringBuilder out = new StringBuilder();
        boolean esc = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (esc) {
                if (c == 'n') out.append('\n');
                else out.append(c);
                esc = false;
            } else {
                if (c == '\\') esc = true;
                else out.append(c);
            }
        }
        return out.toString();
    }

    private String[] split(String line) {
        List<String> parts = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean esc = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (esc) {
                cur.append(c == 'n' ? '\n' : c);
                esc = false;
            } else if (c == '\\') {
                esc = true;
            } else if (c == '|') {
                parts.add(cur.toString());
                cur.setLength(0);
            } else {
                cur.append(c);
            }
        }
        parts.add(cur.toString());
        return parts.toArray(new String[0]);
    }
}
