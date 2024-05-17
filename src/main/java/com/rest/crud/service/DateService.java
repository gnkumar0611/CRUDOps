package com.rest.crud.service;

import com.rest.crud.model.DateTask;

import java.util.List;
import java.util.Optional;

public interface DateService {

    List<DateTask> getAllTasks();
    Optional<DateTask> getTaskById(Long id);
    DateTask saveTask(DateTask task);
    void deleteTask(Long id);
}
