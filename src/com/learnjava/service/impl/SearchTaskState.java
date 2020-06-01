package com.learnjava.service.impl;

import com.learnjava.dao.impl.InMemoryTaskRepository;
import com.learnjava.domain.Task;
import com.learnjava.io.IOManager;
import com.learnjava.io.SimpleConsoleIO;
import com.learnjava.service.State;
import com.learnjava.service.StateEnum;
import com.learnjava.service.StateHelper;

import java.util.List;
import java.util.stream.Collectors;

public class SearchTaskState implements State {
    private static SearchTaskState mySingleInstance = new SearchTaskState();
    private InMemoryTaskRepository taskRepository = InMemoryTaskRepository.getSingleton();
    private IOManager ioManager = new SimpleConsoleIO();

    private SearchTaskState() {
    }

    public static SearchTaskState getSingleton() {
        return mySingleInstance;
    }

    @Override
    public void in() {
        ioManager.clearConsole();
        ioManager.showMessage("Searching a task :");
        on();
    }

    @Override
    public void on() {
        ioManager.showMessage("Insert search text, or empty text to list all tasks :");
        String searchInput = ioManager.readLine().toLowerCase();
        List<Task> filteredTasks = taskRepository.readAll().stream().filter(task -> task.getId().toString().contains(searchInput) ||
                task.getTitle().toLowerCase().contains(searchInput) ||
                task.getDescription().toLowerCase().contains(searchInput) ||
                task.getTime().toString().toLowerCase().contains(searchInput) ||
                task.getDate().toString().toLowerCase().contains(searchInput)).collect(Collectors.toList());
        new StateHelper().listTasks(filteredTasks);
        ioManager.showMessage("Press B to back to dashboard or R to reload:");
        String option = ioManager.readLine();
        if ("B".equalsIgnoreCase(option)) {
            StateManager.getSingleton().getState(StateEnum.DASHBOARD).in();
        } else if ("R".equalsIgnoreCase(option)) {
            reload();
        }
    }

    private void reload() {
        in();
        on();
    }

    @Override
    public void out() {
        ioManager.clearConsole();
    }
}