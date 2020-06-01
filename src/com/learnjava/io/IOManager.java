package com.learnjava.io;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IOManager {
    void showMessage(String message);

    String readLine();

    void clearConsole();

    LocalDate readDate(String pattern);

    LocalTime readTime(String pattern);

    void printf(String message, Object ... toFormat);
}