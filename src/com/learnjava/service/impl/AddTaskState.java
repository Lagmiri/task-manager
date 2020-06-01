package com.learnjava.service.impl;

import com.learnjava.dao.impl.InMemoryTaskRepository;
import com.learnjava.domain.Task;
import com.learnjava.io.IOManager;
import com.learnjava.io.SimpleConsoleIO;
import com.learnjava.service.State;
import com.learnjava.service.StateEnum;
import com.learnjava.service.StateHelper;

public class AddTaskState implements State {
    private static AddTaskState mySingleInstance = new AddTaskState();
    private InMemoryTaskRepository taskRepository = InMemoryTaskRepository.getSingleton();
    private IOManager ioManager = new SimpleConsoleIO();

    private AddTaskState() {
    }

    public static AddTaskState getSingleton() {
        return mySingleInstance;
    }

    @Override
    public void in() {
        ioManager.clearConsole();
        ioManager.showMessage("Adding state!");
        on();
    }

    @Override
    public void on() {
        ioManager.showMessage("Please insert task data :");
        Task task = new StateHelper().scanTaskData();
        ioManager.showMessage("Press S to save; R to retry; C to return to dashboard:");
        String option = ioManager.readLine();
        if ("S".equalsIgnoreCase(option)) {
            taskRepository.add(task);
            ioManager.showMessage("Task successfully added !");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            StateManager.getSingleton().getState(StateEnum.DASHBOARD).in();
        } else if ("R".equalsIgnoreCase(option)) {
            reload();
        } else if ("C".equalsIgnoreCase(option)) {
            StateManager.getSingleton().getState(StateEnum.DASHBOARD).in();
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