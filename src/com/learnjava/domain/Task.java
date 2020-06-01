package com.learnjava.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private LocalTime time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void copyData(Task from){
        this.setTitle(from.getTitle());
        this.setDate(from.getDate());
        this.setDescription(from.getDescription());
        this.setTime(from.getTime());
    }
}