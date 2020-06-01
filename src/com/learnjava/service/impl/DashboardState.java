package com.learnjava.service.impl;

import com.learnjava.io.IOManager;
import com.learnjava.io.SimpleConsoleIO;
import com.learnjava.service.State;
import com.learnjava.service.StateEnum;

public class DashboardState implements State {
        private static  DashboardState single = new DashboardState();
        private IOManager ioManager = new SimpleConsoleIO();
        private State[] states = {};

        private DashboardState(){}

        public static DashboardState getSingleton(){
            return single;
        }

        @Override
        public void in() {
            try{
                ioManager.clearConsole();
                on();
            }catch (RuntimeException ex){
                ioManager.showMessage("The following error occurred : "+ex.getMessage());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {}
                in();
            }

        }

        @Override
        public void on() {
            ioManager.showMessage("Please choose one of the options below :");
            ioManager.showMessage("1) Add a task");
            ioManager.showMessage("2) Show all tasks");
            ioManager.showMessage("3) Search for a task");
            ioManager.showMessage("4) Update a task");
            ioManager.showMessage("5) Delete a task");
            String option = ioManager.readLine();
            State state = getStateByKey(option);
            state.in();
        }

    private State getStateByKey(String option) {
        if(option.startsWith("1")){
            return getState(StateEnum.ADD_TASK);
        }else if(option.startsWith("2")){
            return getState(StateEnum.LIST_TASKS);
        }else if(option.startsWith("3")){
            return getState(StateEnum.SEARCH_TASK);
        }else if(option.startsWith("4")){
            return getState(StateEnum.UPDATE_TASK);
        }else if(option.startsWith("5")){
            return getState(StateEnum.DELETE_TASK);
        }else {
            return getState(StateEnum.DASHBOARD);
        }
    }

    private State getState(StateEnum deleteTask) {
        return StateManager.getSingleton().getState(deleteTask);
    }

    @Override
        public void out() {
            ioManager.clearConsole();
        }
    }