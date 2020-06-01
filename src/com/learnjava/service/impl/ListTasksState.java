package com.learnjava.service.impl;

import com.learnjava.dao.impl.InMemoryTaskRepository;
import com.learnjava.io.IOManager;
import com.learnjava.io.SimpleConsoleIO;
import com.learnjava.service.State;
import com.learnjava.service.StateEnum;
import com.learnjava.service.StateHelper;

public class ListTasksState  implements State{
    private static ListTasksState mySingleInstance = new ListTasksState();
    private InMemoryTaskRepository taskRepository = InMemoryTaskRepository.getSingleton();
    private IOManager ioManager = new SimpleConsoleIO();

    private ListTasksState() {}

    public static ListTasksState getSingleton() {
        return mySingleInstance;
    }

    @Override
    public void in() {
        ioManager.clearConsole();
        ioManager.showMessage("Liste of tasks :");
        on();
    }

    @Override
    public void on() {
        new StateHelper().listTasks(taskRepository.readAll());
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