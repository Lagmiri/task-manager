package com.learnjava;

import com.learnjava.service.StateEnum;
import com.learnjava.service.impl.StateManager;

public class App {
    private static App singleton = new App();

    private App() {
    }

    public void start() {
        new StateManager().getState(StateEnum.DASHBOARD).in();
    }

    public static void main(String[] args) {
        new App().start();
    }
}
