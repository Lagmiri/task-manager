package com.learnjava.service.impl;

import com.learnjava.service.State;
import com.learnjava.service.StateEnum;

public class StateManager {
    private static StateManager stateManager = new StateManager();

    public static StateManager getSingleton() {
        return stateManager;
    }

    public State getState(StateEnum stateEnum) {
        switch (stateEnum) {
            case DASHBOARD:
                return DashboardState.getSingleton();
            case ADD_TASK:
                return AddTaskState.getSingleton();
            case LIST_TASKS:
                return ListTasksState.getSingleton();
            case SEARCH_TASK:
                return SearchTaskState.getSingleton();
            case UPDATE_TASK:
                return UpdateTaskState.getSingleton();
            case DELETE_TASK:
                return DeleteTaskState.getSingleton();
            default:
                throw new IllegalStateException();

        }
    }
}