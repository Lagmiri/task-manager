package com.learnjava.dao.impl;

import com.learnjava.dao.Repository;
import com.learnjava.domain.Task;

import java.util.*;

public class InMemoryTaskRepository implements Repository<Task> {
    private static InMemoryTaskRepository taskRepository = new InMemoryTaskRepository();

    private InMemoryTaskRepository() {
    }

    public static InMemoryTaskRepository getSingleton() {
        return taskRepository;
    }

    private List<Task> tasks = new LinkedList<>();

    @Override
    public Task add(Task data) {
        data.setId((long) tasks.size());
        tasks.add(data);
        return data;
    }

    @Override
    public List<Task> readAll() {
        List<Task> result = Arrays.asList(tasks.toArray(new Task[]{}));
        Collections.copy(result, tasks);
        return result;
    }

    @Override
    public Optional<Task> readOne(Long id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).
                findAny();
    }

    @Override
    public Task update(Task data) {
        Task task = tryToFind(data);
        return task;
    }

    @Override
    public Task remove(Task data) {
        Task task = tryToFind(data);
        tasks.remove(task);
        return task;
    }

    private Task tryToFind(Task data) {
        if (data.getId() == null) {
            throw new IllegalArgumentException("Update or delete the element having id null makes no sense !!!");
        }
        return readOne(data.getId()).orElseThrow(NoSuchElementException::new);
    }
}