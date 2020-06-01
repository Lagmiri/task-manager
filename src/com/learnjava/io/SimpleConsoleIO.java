package com.learnjava.io;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SimpleConsoleIO implements IOManager {
    private PrintStream writer = System.out;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void showMessage(String message) {
        writer.println(message);
    }


    @Override
    public String readLine() {
        String result = null;
        try {
            result = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public LocalDate readDate(String pattern) {
        String inputDate = readLine();
        LocalDate localDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern(pattern));
        return localDate;
    }

    @Override
    public LocalTime readTime(String pattern) {
        String inputDate = readLine();
        LocalTime localTime = LocalTime.parse(inputDate, DateTimeFormatter.ofPattern(pattern));
        return localTime;
    }

    @Override
    public void printf(String message, Object... toFormat) {
        writer.format(message, toFormat);
    }

    @Override
    public void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException e) {
            showMessage("Can't clear screen !");
        }
    }
}