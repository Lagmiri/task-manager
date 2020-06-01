package com.learnjava.service;

import com.learnjava.domain.Task;
import com.learnjava.io.IOManager;
import com.learnjava.io.SimpleConsoleIO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StateHelper {
    private IOManager ioManager = new SimpleConsoleIO();

    public void listTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            ioManager.showMessage("No task was found !");
        } else {
            tasks.forEach(task -> {
                String dashx15 = Stream.generate(()->"-").limit(15).collect(Collectors.joining());
                String dashx50 = Stream.generate(()->"-").limit(50).collect(Collectors.joining());
                ioManager.printf("%15s%50s\n", "+" + dashx15 + "+", dashx50 + "+");
                ioManager.printf("|%15s|%50s|\n", "ID", task.getId());
                ioManager.printf("%15s%50s\n", "+" + dashx15 + "+", dashx50 + "+");
                ioManager.printf("|%15s|%50s|\n", "Title", task.getTitle());
                ioManager.printf("%15s%50s\n", "+" + dashx15 + "+", dashx50 + "+");
                ioManager.printf("|%15s|%50s|\n", "Date", task.getDate().toString());
                ioManager.printf("%15s%50s\n", "+" + dashx15 + "+", dashx50 + "+");
                ioManager.printf("|%15s|%50s|\n", "Time", task.getTime());
                ioManager.printf("%15s%50s\n", "+" + dashx15 + "+", dashx50 + "+");
                ioManager.printf("|%15s|%50s|\n", "Description", task.getDescription());
                ioManager.printf("%15s%50s\n", "+" + dashx15 + "+", dashx50 + "+");
            });
        }
    }

    public Task scanTaskData() {
        Task task = new Task();
        ioManager.showMessage("Title :");
        task.setTitle(ioManager.readLine());
        ioManager.showMessage("Description :");
        task.setDescription(ioManager.readLine());
        ioManager.showMessage("Date (using format \"yyyy-MM-dd\"):");
        task.setDate(ioManager.readDate("yyyy-MM-dd"));
        ioManager.showMessage("Time (using format \"hh:mm:ss\"):");
        task.setTime(ioManager.readTime("HH:mm:ss"));
        return task;
    }
}
