package com.learnjava.service.impl;

import com.learnjava.dao.impl.InMemoryTaskRepository;
import com.learnjava.domain.Task;
import com.learnjava.io.IOManager;
import com.learnjava.io.SimpleConsoleIO;
import com.learnjava.service.State;
import com.learnjava.service.StateEnum;
import com.learnjava.service.StateHelper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class UpdateTaskState implements State {
    private static UpdateTaskState mySingleInstance = new UpdateTaskState();
    private InMemoryTaskRepository taskRepository = InMemoryTaskRepository.getSingleton();
    private IOManager ioManager = new SimpleConsoleIO();

    private UpdateTaskState() {}

    public static UpdateTaskState getSingleton() {
        return mySingleInstance;
    }

    @Override
    public void in() {
        ioManager.clearConsole();
        ioManager.showMessage("Updating a task :");
        on();
    }

    @Override
    public void on() {
        StateHelper stateHelper = new StateHelper();
        ioManager.showMessage("Insert an input to search for the task to update:");
        String searchInput = ioManager.readLine().toLowerCase();
        List<Task> filteredTasks = taskRepository.readAll().stream().filter(task-> task.getId().toString().contains(searchInput) ||
                task.getTitle().toLowerCase().contains(searchInput) ||
                task.getDescription().toLowerCase().contains(searchInput) ||
                task.getTime().toString().toLowerCase().contains(searchInput) ||
                task.getDate().toString().toLowerCase().contains(searchInput)).collect(Collectors.toList());
        stateHelper.listTasks(filteredTasks);
        ioManager.showMessage("Insert the id of the task to update:");
        String idOfStateToUpate = ioManager.readLine().toLowerCase();
        Task taskToUpdate = taskRepository.readOne(Long.parseLong(idOfStateToUpate)).orElseThrow(NoSuchElementException::new);
        ioManager.showMessage("Please insert new data :");
        Task newTaskData = stateHelper.scanTaskData();
        ioManager.showMessage("Press S to save B to back to dashboard or R to reload:");
        String option = ioManager.readLine();
        if ("B".equalsIgnoreCase(option)) {
            StateManager.getSingleton().getState(StateEnum.DASHBOARD).in();
        }else if ("S".equalsIgnoreCase(option)) {
            taskToUpdate.copyData(newTaskData);
            taskRepository.update(taskToUpdate);
            ioManager.showMessage("Saved !");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
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
